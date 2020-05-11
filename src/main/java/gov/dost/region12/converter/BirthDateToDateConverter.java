package gov.dost.region12.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map Date's to Date Util Object.
 */
@Component
public class BirthDateToDateConverter implements Converter<String, Date>{

	static final Logger logger = LoggerFactory.getLogger(BirthDateToDateConverter.class);

	@Override
	public Date convert(String source) {
		
		try {
		    
		    Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		    logger.info("XXXDate : {}", date);
		    return date;
		
		}
		catch (DateTimeParseException exc) {
		    System.out.printf("%s is not parsable!%n", source);
		 
		    return null;
		}
		
		
	}
	

	/**
	 * Gets Date String 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
/*	public LocalDate convert(Object element) {

		String dateStr = String.valueOf(element);
		 LocalDate date = LocalDate.now();
		try {
		    DateTimeFormatter formatter =
		                      DateTimeFormatter.ofPattern("yyyy-mm-dd");
		    date = LocalDate.parse(dateStr, formatter);
		
		}
		catch (DateTimeParseException exc) {
		    System.out.printf("%s is not parsable!%n", element);
		    throw exc;      // Rethrow the exception.
		}
		
		logger.info("XXXDate : {}",date);
		return date;
	}*/
	
}