package gov.dost.region12.service;

import java.util.List;

import gov.dost.region12.model.User;


public interface UserService {
	
	User findById(Long id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);
	
	void deleteUserById(Long id);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Long id, String sso);

}