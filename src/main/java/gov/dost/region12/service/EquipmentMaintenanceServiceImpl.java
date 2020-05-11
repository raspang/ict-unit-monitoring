package gov.dost.region12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dost.region12.dao.EquipmentMaintenanceDao;
import gov.dost.region12.dao.RequestDao;
import gov.dost.region12.dao.UnitDao;
import gov.dost.region12.dao.UserDao;
import gov.dost.region12.model.EquipmentMaintenance;
import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;


@Service("equipmentMaintenanceService")
@Transactional
public class EquipmentMaintenanceServiceImpl implements EquipmentMaintenanceService{

	@Autowired
	private EquipmentMaintenanceDao dao;

	@Autowired
	private UnitDao unitDao;
	
	public EquipmentMaintenance findById(Long id) {
		return dao.findById(id);
	}

	public void saveEquipmentMaintenance(EquipmentMaintenance equipmentMaintenance) {
		dao.save(equipmentMaintenance);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateEquipmentMaintenance(EquipmentMaintenance equipmentMaintenance) {
		EquipmentMaintenance entity = dao.findById(equipmentMaintenance.getId());
		if(entity!=null){
			entity.setRequest(equipmentMaintenance.getRequest());
			entity.setDate(equipmentMaintenance.getDate());
			entity.setOperationPerformed(equipmentMaintenance.getOperationPerformed());
			entity.setPerformedBy(equipmentMaintenance.getPerformedBy());
			entity.setRemark(equipmentMaintenance.getRemark());
		}
	}

	
	public void deleteEquipmentMaintenance(Long id) {
		dao.delete(id);
	}

	@Override
	public List<EquipmentMaintenance> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<EquipmentMaintenance> findByUnit(Unit unit) {
		return dao.findByUnit(unit);
	}

	@Override
	public List<EquipmentMaintenance> findByUnitId(Long unitId) {
		Unit unit = unitDao.findById(unitId);
		return dao.findByUnit(unit);
	}


}
