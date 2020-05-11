package gov.dost.region12.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import gov.dost.region12.model.EquipmentMaintenance;
import gov.dost.region12.model.Unit;

@Repository("equipmentMaintenanceDao")
public class EquipmentMaintenanceDaoImpl extends AbstractDao<Long, EquipmentMaintenance> implements EquipmentMaintenanceDao{

	public EquipmentMaintenance findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void save(EquipmentMaintenance equipmentMaintenance) {
		persist(equipmentMaintenance);
	}

	@Override
	public void delete(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		EquipmentMaintenance equipmentMaintenance = (EquipmentMaintenance)crit.uniqueResult();
		delete(equipmentMaintenance);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentMaintenance> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("id"));
		return (List<EquipmentMaintenance>)crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentMaintenance> findByUnit(Unit unit) {
		Criteria crit = createEntityCriteria();
		crit.createAlias("request", "request");
		crit.add(Restrictions.eq("request.unit", unit));
		crit.addOrder(Order.asc("id"));
		return (List<EquipmentMaintenance>)crit.list();
	}


	
}
