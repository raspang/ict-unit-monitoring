package gov.dost.region12.dao;

import java.util.List;

import gov.dost.region12.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
