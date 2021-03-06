package gov.dost.region12.dao;

import java.util.List;

import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;

public interface UnitDao {
	
	Unit findById(Long id);
	
	void save(Unit request);
	
	void delete(Long id);
	
	List<Unit> findAll();
	
	List<Unit> findAllUnitsByUser(User user);
}
