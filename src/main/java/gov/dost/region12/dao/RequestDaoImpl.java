package gov.dost.region12.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;



@Repository("requestDao")
public class RequestDaoImpl extends AbstractDao<Long, Request>implements RequestDao{

	public Request findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void save(Request request) {
		persist(request);
	}

	@Override
	public void delete(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Request request = (Request)crit.uniqueResult();
		delete(request);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Request> findAll(Long yearReportId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("yearReport", yearReportId));
		crit.addOrder(Order.asc("id"));
		return (List<Request>)crit.list();
	}

	@Override
	public List<Request> findByUnit(Unit unit, Long yearReportId) {
		Criteria crit = createEntityCriteria();
		crit.createAlias("unit", "unit");
		crit.add(Restrictions.eq("unit", unit));
		crit.add(Restrictions.eq("yearReport", yearReportId));
		crit.addOrder(Order.desc("id"));
		return (List<Request>)crit.list();
	}
	
}
