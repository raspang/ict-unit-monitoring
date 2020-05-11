package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.EquipmentMaintenance;
import gov.dost.region12.model.Unit;


public interface EquipmentMaintenanceService {
	
	EquipmentMaintenance findById(Long id);
	
	void saveEquipmentMaintenance(EquipmentMaintenance equipmentMaintenance);
	
	void updateEquipmentMaintenance(EquipmentMaintenance equipmentMaintenance);
	
	void deleteEquipmentMaintenance(Long id);

	List<EquipmentMaintenance> findAll(); 

	List<EquipmentMaintenance> findByUnit(Unit unit); 
	
	List<EquipmentMaintenance> findByUnitId(Long unitId); 
	
}