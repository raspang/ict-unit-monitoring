package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;


public interface UnitService {
	
	Unit findById(Long id);
	
	void saveUnit(Unit unit);
	
	void updateUnit(Unit unit);
	
	void deleteUnit(Long id);

	List<Unit> findAllUnits(); 
	
	List<Unit> findAllUnitsByUser(User user);


}