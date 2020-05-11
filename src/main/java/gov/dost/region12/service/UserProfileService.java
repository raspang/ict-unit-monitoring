package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
