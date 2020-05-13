package gov.dost.region12.dao;

import java.util.List;

import gov.dost.region12.model.PreventiveMaintenance;

public interface PreventiveMaintenanceDao {
	
	PreventiveMaintenance findById(Long id);
	
	void save(PreventiveMaintenance preventiveMaintenance);
	
	void delete(Long id);
	
	List<PreventiveMaintenance> findAll();
}
