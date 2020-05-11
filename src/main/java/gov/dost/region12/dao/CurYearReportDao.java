package gov.dost.region12.dao;

import java.util.List;

import gov.dost.region12.model.YearReport;

public interface CurYearReportDao {
	
	YearReport findByYear(String year);
	
	YearReport findByEnable();
	
	YearReport findById(Long id);
	
	void save(YearReport yearReport);
	
	void delete(Long id);
	
	List<YearReport> findAll();
}
