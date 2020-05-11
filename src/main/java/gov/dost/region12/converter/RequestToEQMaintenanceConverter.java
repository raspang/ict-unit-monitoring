package gov.dost.region12.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import gov.dost.region12.model.Request;
import gov.dost.region12.service.RequestService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RequestToEQMaintenanceConverter implements Converter<Object, Request> {

	static final Logger logger = LoggerFactory.getLogger(RequestToEQMaintenanceConverter.class);

	@Autowired
	RequestService requestService;

	/**
	 * Gets UserProfile by Id
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Request convert(Object element) {

		if(element instanceof Request)
			return (Request)element;
		
		Request request;
		try{
			Long id = Long.parseLong((String) element);
			request = requestService.findById(id);
			logger.info("Request : {}", request);
			return request;
		}catch (Exception e) {
			logger.info("Request : {}", "Empty!!");
			return null;
		}

	}

}