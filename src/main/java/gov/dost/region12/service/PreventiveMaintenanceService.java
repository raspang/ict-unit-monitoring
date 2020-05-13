package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.PreventiveMaintenance;


public interface PreventiveMaintenanceService {
	
	PreventiveMaintenance findById(Long id);
	
	void savePreventiveMaintenance(PreventiveMaintenance preventiveMaintenance);
	
	void updatePreventiveMaintenance(PreventiveMaintenance preventiveMaintenance);
	
	void deletePreventiveMaintenance(Long id);

	List<PreventiveMaintenance> findAllPreventiveMaintenances(); 

}