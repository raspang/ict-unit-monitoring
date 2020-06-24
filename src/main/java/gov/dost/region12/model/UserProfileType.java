package gov.dost.region12.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	EMPLOYEE("EMPLOYEE"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
