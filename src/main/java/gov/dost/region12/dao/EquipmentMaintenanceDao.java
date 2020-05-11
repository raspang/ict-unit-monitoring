package gov.dost.region12.dao;

import java.util.List;

import gov.dost.region12.model.EquipmentMaintenance;
import gov.dost.region12.model.Unit;

public interface EquipmentMaintenanceDao {
	
	EquipmentMaintenance findById(Long id);
	
	void save(EquipmentMaintenance equimentMaintenance);
	
	void delete(Long id);
	
	List<EquipmentMaintenance> findAll();
	
	List<EquipmentMaintenance> findByUnit(Unit unit);
	
	
}
