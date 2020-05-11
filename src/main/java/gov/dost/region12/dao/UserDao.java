package gov.dost.region12.dao;

import java.util.List;

import gov.dost.region12.model.User;


public interface UserDao {

	User findById(Long id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

}

