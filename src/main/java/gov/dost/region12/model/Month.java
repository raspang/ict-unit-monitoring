package gov.dost.region12.model;

public enum Month {
	JANUARY("January"), FEBRUARY("February"), MARCH("March"), APRIL("April"), MAY("May"), JUNE("June"), JULY("July"),
	AUGUST("August"), SEPTEMBER("September"), OCTOBER("October"), NOVEMBER("November"), DECEMBER("December");

	Month(String month) {
		this.month = month;
	}

	private String month;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
