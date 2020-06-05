package gov.dost.region12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dost.region12.dao.CurYearReportDao;
import gov.dost.region12.dao.PreventiveMaintenanceDao;
import gov.dost.region12.dao.RequestDao;
import gov.dost.region12.dao.UnitDao;
import gov.dost.region12.dao.UserDao;
import gov.dost.region12.model.PreventiveMaintenance;
import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;


@Service("preventiveMaintenanceService")
@Transactional
public class PreventiveMaintenanceServiceImpl implements PreventiveMaintenanceService{

	@Autowired
	private PreventiveMaintenanceDao dao;

	@Autowired
	private CurYearReportDao curYearReportDao; 

	
	public PreventiveMaintenance findById(Long id) {
		return dao.findById(id);
	}

	public void savePreventiveMaintenance(PreventiveMaintenance preventiveMaintenance) {
		preventiveMaintenance.setYearReport(curYearReportDao.findByEnable().getId());
		dao.save(preventiveMaintenance);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updatePreventiveMaintenance(PreventiveMaintenance preventiveMaintenance) {
		PreventiveMaintenance entity = dao.findById(preventiveMaintenance.getId());
		if(entity!=null){
			entity.setUnit(preventiveMaintenance.getUnit());
			entity.setPreparedBy(preventiveMaintenance.getPreparedBy());
			entity.setPreparedDate(preventiveMaintenance.getPreparedDate());
			entity.setApprovedBy(preventiveMaintenance.getApprovedBy());
			entity.setApprovedDate(preventiveMaintenance.getApprovedDate());
			entity.setCheckAll(preventiveMaintenance.getCheckAll());
			entity.setCheck1(preventiveMaintenance.getCheck1());
			entity.setCheck2(preventiveMaintenance.getCheck2());
			entity.setCheck3(preventiveMaintenance.getCheck3());
			entity.setCheck4(preventiveMaintenance.getCheck4());
			entity.setCheck5(preventiveMaintenance.getCheck5());
			entity.setCheck6(preventiveMaintenance.getCheck6());
			entity.setCheck7(preventiveMaintenance.getCheck7());
			entity.setCheck8(preventiveMaintenance.getCheck8());
			entity.setCheck9(preventiveMaintenance.getCheck9());
			entity.setCheck10(preventiveMaintenance.getCheck10());
			entity.setCheck11(preventiveMaintenance.getCheck11());
			entity.setCheck12(preventiveMaintenance.getCheck12());
			entity.setCheck13(preventiveMaintenance.getCheck13());
			entity.setCheck14(preventiveMaintenance.getCheck14());
			entity.setCompleted(preventiveMaintenance.isCompleted());
		}
	}

	
	public void deletePreventiveMaintenance(Long id) {
		dao.delete(id);
	}

	@Override
	public List<PreventiveMaintenance> findAllPreventiveMaintenances() {
		return dao.findAll(curYearReportDao.findByEnable().getId());
	}

	@Override
	public List<PreventiveMaintenance> findByCompleted() {
		return dao.findByCompleted(curYearReportDao.findByEnable().getId());
	}


}
