package gov.dost.region12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	
	public PreventiveMaintenance findById(Long id) {
		return dao.findById(id);
	}

	public void savePreventiveMaintenance(PreventiveMaintenance preventiveMaintenance) {
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
		}
	}

	
	public void deletePreventiveMaintenance(Long id) {
		dao.delete(id);
	}

	@Override
	public List<PreventiveMaintenance> findAllPreventiveMaintenances() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


}
