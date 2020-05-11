package gov.dost.region12.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dost.region12.dao.CurYearReportDao;
import gov.dost.region12.model.YearReport;


@Service("curYearReportService")
@Transactional
public class CurYearReportServiceImpl implements CurYearReportService{

	@Autowired
	private CurYearReportDao dao;


	@Override
	public YearReport findByEnable() {
		// TODO Auto-generated method stub
		return dao.findByEnable();
	}

	
	public YearReport findById(Long id) {
		return dao.findById(id);
	}

	public void saveYearReport(YearReport yearReport) {
		dao.save(yearReport);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateYearReport(YearReport yearReport) {
		// if there is enable and findAllYearReports is not empty and yearReport is Enable
		if( findByEnable() != null
				&& findByEnable().getId()!= yearReport.getId()
				&& findAllYearReports().size()> 0 
				&& yearReport.isEnable()){
			YearReport  yearReportEnable = findByEnable();
			yearReportEnable.setEnable(false);
		}
		
			
		YearReport entity = dao.findById(yearReport.getId());
		if(entity!=null){
			entity.setEnable(yearReport.isEnable());
			entity.setYear(yearReport.getYear());
		}
	}

	
	public void deleteYearReport(Long id) {
		dao.delete(id);
	}

	@Override
	public List<YearReport> findAllYearReports() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}


	@Override
	public YearReport findByYear(String year) {
		// TODO Auto-generated method stub
		return dao.findByYear(year);
	}



}
