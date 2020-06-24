package gov.dost.region12.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;



@Repository("unitDao")
public class UnitDaoImpl extends AbstractDao<Long, Unit> implements UnitDao{

	public Unit findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void save(Unit unit) {
		persist(unit);
	}

	@Override
	public void delete(Long id) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Unit unit = (Unit)crit.uniqueResult();
		delete(unit);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("id"));
		return (List<Unit>)crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> findAllUnitsByUser(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("receivedBy", user));
		crit.addOrder(Order.asc("id"));
		return (List<Unit>)crit.list();
	}
	
}
