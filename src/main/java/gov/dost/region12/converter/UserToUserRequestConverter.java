package gov.dost.region12.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import gov.dost.region12.model.Unit;
import gov.dost.region12.model.User;
import gov.dost.region12.model.UserProfile;
import gov.dost.region12.service.UserProfileService;
import gov.dost.region12.service.UserService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class UserToUserRequestConverter implements Converter<Object, User>{

	static final Logger logger = LoggerFactory.getLogger(UserToUserRequestConverter.class);
	
	@Autowired
	UserService userService;

	/**
	 * Gets UserProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public User convert(Object element) {
	
		if(element instanceof User)
			return (User)element;
		
		try {
			Long id = Long.parseLong((String)element);
			User user= userService.findById(id);
			return user; 
		}catch (Exception e) {
			// TODO: handle exception
			logger.info("XXXUser : {}", element);
			return null; 	
		}
		
	
	}
	 
	
}