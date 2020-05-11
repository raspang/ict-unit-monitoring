package gov.dost.region12.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;
import gov.dost.region12.model.YearReport;



@Repository("curYearReportDao")
public class CurYearReportDaoImpl extends AbstractDao<Long, YearReport> implements CurYearReportDao{

	public YearReport findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void save(YearReport yearReport) {
		persist(yearReport);
	}

	@Override
	public void delete(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		YearReport yearReport = (YearReport)crit.uniqueResult();
		delete(yearReport);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearReport> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("id"));
		return (List<YearReport>)crit.list();
	}

	@Override
	public YearReport findByEnable() {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("enable", true));
		YearReport yearReport = (YearReport)crit.uniqueResult();
		return yearReport;
	}

	@Override
	public YearReport findByYear(String year) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("year", year));
		YearReport yearReport = (YearReport)crit.uniqueResult();
		return yearReport;
	}
	
}
