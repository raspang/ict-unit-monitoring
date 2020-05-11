package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.Unit;


public interface UnitService {
	
	Unit findById(Long id);
	
	void saveUnit(Unit unit);
	
	void updateUnit(Unit unit);
	
	void deleteUnit(Long id);

	List<Unit> findAllUnits(); 


}