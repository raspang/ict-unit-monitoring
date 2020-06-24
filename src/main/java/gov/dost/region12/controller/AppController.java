package gov.dost.region12.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import gov.dost.region12.model.EquipmentMaintenance;
import gov.dost.region12.model.PreventiveMaintenance;
import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;
import gov.dost.region12.model.UserProfile;
import gov.dost.region12.model.UserProfileType;
import gov.dost.region12.model.YearReport;
import gov.dost.region12.service.CurYearReportService;
import gov.dost.region12.service.EquipmentMaintenanceService;
import gov.dost.region12.service.PreventiveMaintenanceService;
import gov.dost.region12.service.RequestService;
import gov.dost.region12.service.UnitService;
import gov.dost.region12.service.UserProfileService;
import gov.dost.region12.service.UserService;
import gov.dost.region12.util.JasperReportUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	RequestService requestService;

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UnitService unitService;

	@Autowired
	EquipmentMaintenanceService equipmentMaintenanceService;

	@Autowired
	CurYearReportService curYearReportService;

	@Autowired
	PreventiveMaintenanceService preventiveMaintenanceService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	/* Json */
	@RequestMapping("/admin/json/data/requests")
	@ResponseBody
	public List<Request> getRequests() {
		return requestService.findAllRequests();
	}

	@RequestMapping("/admin/json/data/users")
	@ResponseBody
	public List<User> getUsers() {
		return userService.findAllUsers();
	}

	@RequestMapping("/admin/json/data/units")
	@ResponseBody
	public List<Unit> getUnits() {
		return unitService.findAllUnits();
	}

	@RequestMapping("/admin/json/data/{unitId}/equipmentMaintenances")
	@ResponseBody
	public List<EquipmentMaintenance> getEquipmentMaintenances(@PathVariable Long unitId) {
		return equipmentMaintenanceService.findByUnitId(unitId);
	}

	@RequestMapping("/admin/json/data/preventives")
	@ResponseBody
	public List<PreventiveMaintenance> getPreventiveMaintenances() {
		return preventiveMaintenanceService.findAllPreventiveMaintenances();
	}
	/* end Json */

	/* Controller */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());

		if (!isCurrentAuthenticationAnonymous() && getUserRole().equals(UserProfileType.ADMIN.getUserProfileType()))
			return "redirect:/admin/request";
		else
			return "index";

	}

	@RequestMapping(value = { "/admin/user" }, method = RequestMethod.GET)
	public String user(ModelMap model) {

		model.addAttribute("loggedinuser", getPrincipal());
		return "userList";
	}

	@RequestMapping(value = { "/admin/request" }, method = RequestMethod.GET)
	public String request(ModelMap model) {

		model.addAttribute("loggedinuser", getPrincipal());
		return "requestList";
	}

	@RequestMapping(value = { "/admin/unit" }, method = RequestMethod.GET)
	public String unit(ModelMap model) {

		model.addAttribute("loggedinuser", getPrincipal());
		return "unitList";
	}

	@RequestMapping(value = { "/admin/maintenance" }, method = RequestMethod.GET)
	public String maintenance(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "equipmentMaintenanceList";
	}

	@RequestMapping(value = { "/admin/view-maintenance-unit-{unitId}" }, method = RequestMethod.GET)
	public String maintenanceUnit(ModelMap model, @PathVariable Long unitId) {
		Unit unit = unitService.findById(unitId);
		model.addAttribute("unit", unit);
		model.addAttribute("loggedinuser", getPrincipal());
		return "equipmentMaintenanceUnitList";
	}

	@RequestMapping(value = { "/admin/preventive" }, method = RequestMethod.GET)
	public String preventive(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "preventiveMaintenanceList";
	}

	/* end Controller */

	@RequestMapping(value = { "/admin/newpreventivemaintenance" }, method = RequestMethod.GET)
	public String newPreventive(ModelMap model) {

		model.addAttribute("months", new String[] { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" });
		model.addAttribute("units", unitService.findAllUnits());
		model.addAttribute("users", userService.findAllUsers());
		model.addAttribute("preventiveMaintenance", new PreventiveMaintenance());
		model.addAttribute("edit", false);

		model.addAttribute("loggedinuser", getPrincipal());
		return "preventiveMaintenance";
	}

	@RequestMapping(value = { "/admin/newpreventivemaintenance" }, method = RequestMethod.POST)
	public String savePreventive(@Valid PreventiveMaintenance preventiveMaintenance, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return "preventiveMaintenance";
		}
		preventiveMaintenanceService.savePreventiveMaintenance(preventiveMaintenance);
		model.addAttribute("success", "Preventive Maintenance Schedule successfully created.");
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/preventive";
	}

	@RequestMapping(value = { "/admin/edit-preventivemaintenance-{preventiveId}" }, method = RequestMethod.GET)
	public String editPreventive(@PathVariable Long preventiveId, ModelMap model) {
		PreventiveMaintenance preventiveMaintenance = preventiveMaintenanceService.findById(preventiveId);
		model.addAttribute("months", new String[] { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" });
		model.addAttribute("units", unitService.findAllUnits());
		model.addAttribute("users", userService.findAllUsers());
		model.addAttribute("preventiveMaintenance", preventiveMaintenance);
		model.addAttribute("checkAll", preventiveMaintenance.getCheckAll());
		model.addAttribute("edit", true);

		model.addAttribute("loggedinuser", getPrincipal());
		return "preventiveMaintenance";
	}

	@RequestMapping(value = { "/admin/edit-preventivemaintenance1-{preventiveId}" }, method = RequestMethod.POST)
	public String updatePreventive(@Valid PreventiveMaintenance preventiveMaintenance, BindingResult result,
			ModelMap model, @PathVariable Long preventiveId) {
		if (result.hasErrors()) {
			return "preventiveMaintenance";
		}
		preventiveMaintenanceService.updatePreventiveMaintenance(preventiveMaintenance);
		model.addAttribute("success", "Preventive Maintenance Schedule successfully updated.");
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/preventive";
	}

	@RequestMapping(value = { "/admin/delete-preventivemaintenance-{preventiveId}" }, method = RequestMethod.GET)
	public String deletePreventive(@PathVariable Long preventiveId) {
		preventiveMaintenanceService.deletePreventiveMaintenance(preventiveId);
		return "redirect:/admin/preventive";
	}

	@RequestMapping(value = { "/admin/newequipmentmaintenance-unit-{unitId}" }, method = RequestMethod.GET)
	public String newEquipmentMaintenance(ModelMap model, @PathVariable Long unitId) {
		EquipmentMaintenance equipmentMaintenance = new EquipmentMaintenance();
		List<User> users = userService.findAllUsers();
		List<Request> requests = requestService.findNotInEquipmentMaintenance(unitService.findById(unitId));

		List<User> usersInit = new ArrayList<>();
		List<Request> requestsInit = new ArrayList<>();

		usersInit.add(new User());
		usersInit.addAll(users);
		requestsInit.addAll(requests);

		model.addAttribute("equipmentMaintenance", equipmentMaintenance);
		model.addAttribute("requests", requestsInit);
		model.addAttribute("users", usersInit);
		model.addAttribute("unitId", unitId);
		model.addAttribute("edit", false);

		model.addAttribute("loggedinuser", getPrincipal());
		return "equipmentMaintenanceUnit";
	}

	@RequestMapping(value = { "/admin/newequipmentmaintenance-unit" }, method = RequestMethod.POST)
	public String saveEquipmentMaintenance(@Valid EquipmentMaintenance equipmentMaintenance, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "equipmentMaintenanceUnit";
		}
		Long unitId = 0L;
		if (equipmentMaintenance.getRequest() != null && equipmentMaintenance.getRequest().getUnit() != null) {
			Unit unit = equipmentMaintenance.getRequest().getUnit();
			unitId = unit.getId();
		}
		equipmentMaintenanceService.saveEquipmentMaintenance(equipmentMaintenance);
		model.addAttribute("success", "Equipment Maintenance successfully created.");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		if (unitId != 0L)
			return "redirect:/admin/view-maintenance-unit-" + unitId;
		return "redirect:/admin/maintenance";
	}

	@RequestMapping(value = { "/admin/edit-equipmentmaintenance-{equipmentmaintenanceId}" }, method = RequestMethod.GET)
	public String editEquipmentMaintenance(@PathVariable Long equipmentmaintenanceId, ModelMap model) {

		EquipmentMaintenance equipmentMaintenance = equipmentMaintenanceService.findById(equipmentmaintenanceId);
		List<User> users = userService.findAllUsers();

		List<User> usersInit = new ArrayList<>();
		List<Request> requestsInit = new ArrayList<>();

		usersInit.add(new User());
		usersInit.addAll(users);

		requestsInit.add(equipmentMaintenance.getRequest());

		Long unitId = 0L;
		if (equipmentMaintenance.getRequest() != null && equipmentMaintenance.getRequest().getUnit() != null)
			unitId = equipmentMaintenance.getRequest().getUnit().getId();

		model.addAttribute("equipmentMaintenance", equipmentMaintenance);
		model.addAttribute("requests", requestsInit);
		model.addAttribute("users", usersInit);
		model.addAttribute("unitId", unitId);
		model.addAttribute("edit", true);

		model.addAttribute("loggedinuser", getPrincipal());
		return "equipmentMaintenanceUnit";
	}

	@RequestMapping(value = { "/admin/editequipmentmaintenance-unit" }, method = RequestMethod.POST)
	public String updateEquipmentMaintenance(@Valid EquipmentMaintenance equipmentMaintenance, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "equipmentMaintenanceUnit";
		}
		Long unitId = 0L;
		if (equipmentMaintenance.getRequest() != null && equipmentMaintenance.getRequest().getUnit() != null)
			unitId = equipmentMaintenance.getRequest().getUnit().getId();

		equipmentMaintenanceService.updateEquipmentMaintenance(equipmentMaintenance);
		model.addAttribute("success", "Equipment Maintenance successfully created.");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		if (unitId != 0L)
			return "redirect:/admin/view-maintenance-unit-" + unitId;
		return "redirect:/admin/maintenance";
	}

	@RequestMapping(value = { "/admin/delete-equipmentmaintenance-{id}" }, method = RequestMethod.GET)
	public String deleteEquipmentMaintenance(@PathVariable Long id) {
		Long unitId = 0L;
		EquipmentMaintenance equipmentMaintenance = equipmentMaintenanceService.findById(id);
		if (equipmentMaintenance.getRequest() != null && equipmentMaintenance.getRequest().getUnit() != null)
			unitId = equipmentMaintenance.getRequest().getUnit().getId();

		equipmentMaintenanceService.deleteEquipmentMaintenance(id);

		if (unitId != 0L)
			return "redirect:/admin/view-maintenance-unit-" + unitId;
		return "redirect:/admin/maintenance";
	}

	@RequestMapping(value = { "/admin/newunit" }, method = RequestMethod.GET)
	public String newUnit(ModelMap model) {
		Unit unit = new Unit();

		List<User> users = userService.findAllUsers();
		List<User> usersInit = new ArrayList<>();

		usersInit.add(new User());
		usersInit.addAll(users);

		model.addAttribute("unit", unit);
		model.addAttribute("users", usersInit);
		model.addAttribute("edit", false);

		model.addAttribute("loggedinuser", getPrincipal());
		return "unit";
	}

	@RequestMapping(value = { "/admin/newunit" }, method = RequestMethod.POST)
	public String saveUnit(@Valid Unit unit, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "unit";
		}

		unitService.saveUnit(unit);
		model.addAttribute("success", "Unit successfully created.");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "redirect:/admin/unit";
	}

	@RequestMapping(value = { "/admin/edit-unit-{id}" }, method = RequestMethod.GET)
	public String editUnit(@PathVariable Long id, ModelMap model) {
		Unit unit = unitService.findById(id);
		List<User> users = userService.findAllUsers();

		List<User> usersInit = new ArrayList<>();

		usersInit.add(new User());
		usersInit.addAll(users);

		model.addAttribute("unit", unit);
		model.addAttribute("users", usersInit);

		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "unit";
	}

	@RequestMapping(value = { "/admin/edit-unit-{id}" }, method = RequestMethod.POST)
	public String updateUnit(@Valid Unit unit, BindingResult result, ModelMap model, @PathVariable Long id) {
		if (result.hasErrors()) {
			return "unit";
		}
		unitService.updateUnit(unit);
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/unit";
	}

	@RequestMapping(value = { "/admin/delete-unit-{id}" }, method = RequestMethod.GET)
	public String deleteUnit(@PathVariable Long id) {
		unitService.deleteUnit(id);
		return "redirect:/admin/unit";
	}

	/* ------ unit ---- */

	@RequestMapping(value = { "/admin/newrequest" }, method = RequestMethod.GET)
	public String newRequest(ModelMap model) {
		Request request = new Request();
		List<User> users = userService.findAllUsers();
		List<Unit> units = unitService.findAllUnits();

		List<Unit> unitsInit = new ArrayList<>();
		List<User> usersInit = new ArrayList<>();

		usersInit.add(new User());
		unitsInit.add(new Unit());

		usersInit.addAll(users);
		unitsInit.addAll(units);

		model.addAttribute("request", request);
		model.addAttribute("users", usersInit);
		model.addAttribute("units", unitsInit);

		model.addAttribute("edit", false);

		model.addAttribute("loggedinuser", getPrincipal());
		return "request";
	}

	@RequestMapping(value = { "/admin/newrequest" }, method = RequestMethod.POST)
	public String saveRequest(@Valid Request request, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "request";
		}

		requestService.saveRequest(request);

		model.addAttribute("success", "Request successfully created.");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "redirect:/admin/request";
	}

	@RequestMapping(value = { "/admin/edit-request-{id}" }, method = RequestMethod.GET)
	public String editRequest(@PathVariable Long id, ModelMap model) {
		Request request = requestService.findById(id);
		List<User> users = userService.findAllUsers();
		List<Unit> units = unitService.findAllUnits();

		List<Unit> unitsInit = new ArrayList<>();
		List<User> usersInit = new ArrayList<>();

		usersInit.add(new User());
		unitsInit.add(new Unit());

		usersInit.addAll(users);
		unitsInit.addAll(units);

		model.addAttribute("request", request);
		model.addAttribute("users", usersInit);
		model.addAttribute("units", unitsInit);

		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "request";
	}

	@RequestMapping(value = { "/admin/edit-request-{id}" }, method = RequestMethod.POST)
	public String updateRequest(@Valid Request request, BindingResult result, ModelMap model, @PathVariable Long id) {
		if (result.hasErrors()) {
			return "request";
		}
		requestService.updateRequest(request);
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/request";
	}

	@RequestMapping(value = { "/admin/delete-request-{id}" }, method = RequestMethod.GET)
	public String deleteRequest(@PathVariable Long id) {
		requestService.deleteRequest(id);
		return "redirect:/admin/request";
	}

	/* Setting Current Year Report */
	@RequestMapping(value = { "/admin/yearreport" }, method = RequestMethod.GET)
	public String newCurYearReport(ModelMap model) {
		model.addAttribute("yearReports", curYearReportService.findAllYearReports());
		return "yearreport";
	}

	@RequestMapping(value = { "/admin/newcurrentyearreport" }, method = RequestMethod.GET)
	public String saveCurYearReport(@RequestParam(name = "year") String year, HttpServletRequest request,
			ModelMap model) {

		if (curYearReportService.findByYear(year) != null) {
			YearReport yearReport = curYearReportService.findByYear(year);
			yearReport.setYear(year);
			yearReport.setEnable(true);
			curYearReportService.updateYearReport(yearReport);
		} else {

			YearReport yearReportEnable = curYearReportService.findByEnable();
			if (yearReportEnable != null) {
				yearReportEnable.setEnable(false);
				curYearReportService.updateYearReport(yearReportEnable);
			}

			YearReport yearReport = new YearReport();
			yearReport.setYear(year);
			yearReport.setEnable(true);
			;
			curYearReportService.saveYearReport(yearReport);
		}

		return "redirect:/admin/yearreport";
	}

	@RequestMapping(value = { "/admin/objective" }, method = RequestMethod.GET)
	public String objective(ModelMap model) {

		model.addAttribute("noUnits", unitService.findAllUnits().size());
		model.addAttribute("noPreventiveManagementPerformed", preventiveMaintenanceService.findByCompleted().size());

		return "objective";
	}

	@RequestMapping(value = "/admin/{format}/pdf")
	public String generateReport(@RequestParam(name = "id") String id, @PathVariable String format,
			HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, NamingException {

		String reportFileName = "request form";
		JasperReportUtil jrdao = new JasperReportUtil();

		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		List<Map<String, ?>> listCodes = new ArrayList<Map<String, ?>>();

		if (format.equals("request")) {
			reportFileName = "request form";
	//		for (Request r : requestService.findAllRequests()) {
				Request r = requestService.findById(Long.parseLong(id));
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("item", r.getUnit().getEquipmentName());
				m.put("date1", dateConvert(r.getDate()));
				m.put("division", r.getRequestBy().getDivision());
				m.put("descModel", r.getUnit().getOtherComponent());
				m.put("serialNumber", r.getUnit().getSerialNo());
				m.put("descMalfunction", r.getDescriptionOfMalfunction());
				m.put("receivedBy", r.getUnit().getReceivedBy().getFullName());
				m.put("date2", dateConvert(r.getUnit().getDateRecieved()));
				m.put("requestBy", r.getRequestBy() != null ? r.getRequestBy().getFullName() : "");
				m.put("recommendedBy", r.getRecommendedBy() != null ? r.getRecommendedBy().getFullName() : "");
				m.put("recommendation", r.getRecommendation());
				m.put("inspectedBy", r.getInspectedBy() != null ? r.getInspectedBy().getFullName() : "");
				m.put("noted", r.getNotedBy() != null ? r.getNotedBy().getFullName() : "");			
				m.put("repairedBy", r.getInHouseRepairedBy() != null ? r.getInHouseRepairedBy().getFullName() : "");
				m.put("receivedBy2", r.getInHouseReceivedBy() != null ? r.getInHouseReceivedBy().getFullName() : "");
				listCodes.add(m);
//
//			?
			// hmParams.put("item",requestService.findAllRequests().get(0).getUnit().getEquipmentName());
		}
		JasperReport jasperReport = jrdao.getCompiledFile(reportFileName, request);
		try {
			jrdao.generateReportPDF(response, hmParams, listCodes, jasperReport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // For
			// PDF
			// report

		return null;
	}

	private String dateConvert(Date date) {
		if (date == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		return strDate;
	}
	/**
	 * This method will list all existing users.
	 */
	/*
	 * @RequestMapping(value = { "/list"}, method = RequestMethod.GET) public String
	 * listUsers(ModelMap model) { List<User> users = userService.findAllUsers();
	 * model.addAttribute("users", users); model.addAttribute("loggedinuser",
	 * getPrincipal()); return "userslist"; }
	 */

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/admin/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "user";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/admin/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "user";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing
		 * custom @Unique annotation and applying it on field [sso] of Model class
		 * [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill
		 * custom errors outside the validation framework as well while still using
		 * internationalized messages.
		 * 
		 */
		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			return "user";
		}

		userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "redirect:/admin/user";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/admin/edit-user-{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long id, ModelMap model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "user";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/admin/edit-user-{id}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable Long id) {

		if (result.hasErrors()) {
			return "user";
		}

		userService.updateUser(user);

		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/user";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/admin/delete-user-{id}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return "redirect:/admin/user";
	}

	@RequestMapping(value = { "/employee/newrequest" }, method = RequestMethod.GET)
	public String newRequestEmployee(ModelMap model) {
		Request request = new Request();
		User user = userService.findBySSO(getPrincipal());
		List<Unit> units = unitService.findAllUnitsByUser(user);
		model.addAttribute("request", request);
		model.addAttribute("users", Arrays.asList(user));
		model.addAttribute("units", units);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "request";
	}

	@RequestMapping(value = { "/employee/newrequest" }, method = RequestMethod.POST)
	public String saveRequestEmployee(@Valid Request request, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "request";
		}
		requestService.saveRequest(request);
		model.addAttribute("success", "Request successfully created.");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "success";
	}

	/**
	 * This method will provide current year report
	 */

	@ModelAttribute("loggedinuser")
	public String getLogggedInUser() {
		return getPrincipal();
	}

	@ModelAttribute("currentYearReport")
	public YearReport currentYearReport() {
		return curYearReportService.findByEnable();
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else if (getUserRole() == UserProfileType.EMPLOYEE.getUserProfileType())
			return "index";
		else {
			return "redirect:/admin/request";
		}
	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 * RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else
	 * false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	private String getUserRole() {
		User user = userService.findBySSO(getPrincipal());
		Iterator<UserProfile> itr = (Iterator<UserProfile>) user.getUserProfiles().iterator();

		while (itr.hasNext())
			return itr.next().getType();

		return UserProfileType.EMPLOYEE.getUserProfileType();

	}

}