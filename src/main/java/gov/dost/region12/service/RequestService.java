package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.Request;
import gov.dost.region12.model.Unit;


public interface RequestService {
	
	Request findById(Long id);
	
	void saveRequest(Request request);
	
	void updateRequest(Request request);
	
	void deleteRequest(Long id);

	List<Request> findAllRequests(); 

	List<Request> findNotInEquipmentMaintenance(Unit unit);

}