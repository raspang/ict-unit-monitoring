package gov.dost.region12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dost.region12.dao.RequestDao;
import gov.dost.region12.dao.UnitDao;
import gov.dost.region12.dao.UserDao;
import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;


@Service("unitService")
@Transactional
public class UnitServiceImpl implements UnitService{

	@Autowired
	private UnitDao dao;


	
	public Unit findById(Long id) {
		return dao.findById(id);
	}

	public void saveUnit(Unit unit) {
		dao.save(unit);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUnit(Unit unit) {
		Unit entity = dao.findById(unit.getId());
		if(entity!=null){
			entity.setEquipmentName(unit.getEquipmentName());
			entity.setCodeNo(unit.getCodeNo());
			entity.setModelNo(unit.getModelNo());
			entity.setSerialNo(unit.getSerialNo());
			entity.setLocation(unit.getLocation());
			entity.setOtherComponent(unit.getOtherComponent());
			entity.setDateAcquired(unit.getDateAcquired());
			entity.setDateRecieved(unit.getDateRecieved());
			entity.setReceivedBy(unit.getReceivedBy());
		}
	}

	
	public void deleteUnit(Long id) {
		dao.delete(id);
	}

	@Override
	public List<Unit> findAllUnits() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Unit> findAllUnitsByUser(User user) {
		// TODO Auto-generated method stub
		return dao.findAllUnitsByUser(user);
	}


}
