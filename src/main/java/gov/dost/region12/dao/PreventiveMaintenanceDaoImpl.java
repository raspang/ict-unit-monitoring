package gov.dost.region12.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import gov.dost.region12.model.PreventiveMaintenance;
import gov.dost.region12.model.Unit;



@Repository("preventiveMaintenanceDao")
public class PreventiveMaintenanceDaoImpl extends AbstractDao<Long, PreventiveMaintenance> implements PreventiveMaintenanceDao{

	public PreventiveMaintenance findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void save(PreventiveMaintenance preventiveMaintenance) {
		persist(preventiveMaintenance);
	}

	@Override
	public void delete(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		PreventiveMaintenance preventiveMaintenance = (PreventiveMaintenance)crit.uniqueResult();
		delete(preventiveMaintenance);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PreventiveMaintenance> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("id"));
		return (List<PreventiveMaintenance>)crit.list();
	}
	
}
