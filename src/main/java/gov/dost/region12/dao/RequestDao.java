package gov.dost.region12.dao;

import java.util.List;

import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;


public interface RequestDao {

	Request findById(Long id);
	
	void save(Request request);
	
	void delete(Long id);
	
	List<Request> findAll(Long yearReportId);
	
	List<Request> findByUnit(Unit unit, Long yearReportId);

}

