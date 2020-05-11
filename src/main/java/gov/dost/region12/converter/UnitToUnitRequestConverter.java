package gov.dost.region12.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import gov.dost.region12.model.Unit;
import gov.dost.region12.service.UnitService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class UnitToUnitRequestConverter implements Converter<Object, Unit> {

	static final Logger logger = LoggerFactory.getLogger(UnitToUnitRequestConverter.class);

	@Autowired
	UnitService unitService;

	/**
	 * Gets UserProfile by Id
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Unit convert(Object element) {

		if(element instanceof Unit)
			return (Unit)element;
		
		Unit unit;
		try{
			Long id = Long.parseLong((String) element);
			unit = unitService.findById(id);
			logger.info("XXXUnit : {}", unit);
			return unit;
		}catch (Exception e) {
			logger.info("XXXUnit : {}", "Empty!!");
			return null;
		}


		

	}

}