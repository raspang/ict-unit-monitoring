package gov.dost.region12.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dost.region12.dao.CurYearReportDao;
import gov.dost.region12.dao.EquipmentMaintenanceDao;
import gov.dost.region12.dao.RequestDao;
import gov.dost.region12.dao.UserDao;
import gov.dost.region12.model.EquipmentMaintenance;
import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;


@Service("requestService")
@Transactional
public class RequestServiceImpl implements RequestService{

	@Autowired
	private RequestDao dao;
	
	@Autowired
	private CurYearReportDao curYearReportDao; 

	@Autowired
	private EquipmentMaintenanceDao equipmentMaintenanceDao;
	
	public Request findById(Long id) {
		return dao.findById(id);
	}

	public void saveRequest(Request request) {
		request.setYearReport(curYearReportDao.findByEnable().getId());
		dao.save(request);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateRequest(Request request) {
		Request entity = dao.findById(request.getId());
		if(entity!=null){
			entity.setUnit(request.getUnit());
			entity.setDate(request.getDate());
			entity.setDescriptionOfMalfunction(request.getDescriptionOfMalfunction());
			//entity.setReceivedBy(request.getReceivedBy());
			//entity.setDateRecieved(request.getDateRecieved());
			entity.setRequestBy(request.getRequestBy());
			entity.setRecommendation(request.getRecommendation());
			entity.setRecommendedBy(request.getRecommendedBy());
			entity.setInspectedBy(request.getInspectedBy());
			entity.setNotedBy(request.getNotedBy());
			entity.setInHouseRepairedBy(request.getInHouseRepairedBy());
			entity.setInHouseReceivedBy(request.getInHouseReceivedBy());
			entity.setIsApproved(request.getIsApproved());
		}
	}

	
	public void deleteRequest(Long id) {
		dao.delete(id);
	}

	@Override
	public List<Request> findAllRequests() {
		// TODO Auto-generated method stub
		return dao.findAll(curYearReportDao.findByEnable().getId());
	}

	@Override
	public List<Request> findNotInEquipmentMaintenance(Unit unit) {
		List<Long> idRequests = new ArrayList<>();
		List<Request> reqs = new ArrayList<>();
		
		// Get requests for this unit
		for(EquipmentMaintenance eM : equipmentMaintenanceDao.findAll(curYearReportDao.findByEnable().getId())) 
			if(eM.getRequest() != null)
				idRequests.add(eM.getRequest().getId());
		
		// filter all request for this unit
		for(Request r : dao.findByUnit(unit, curYearReportDao.findByEnable().getId())){
			boolean isIn = false;
			for(Long id : idRequests)
				if(r.getId().equals(id))
					isIn = true;
			if(!isIn)
				reqs.add(r);
		}
		return reqs;
	}


}
