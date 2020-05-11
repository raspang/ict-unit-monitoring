package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.YearReport;


public interface CurYearReportService {
	
	YearReport findByYear(String year);
	
	YearReport findByEnable();
	
	YearReport findById(Long id);
	
	void saveYearReport(YearReport yearReport);
	
	void updateYearReport(YearReport yearReport);
	
	void deleteYearReport(Long id);

	List<YearReport> findAllYearReports(); 


}