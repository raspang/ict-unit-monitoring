package gov.dost.region12.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="REQUEST")
public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="unit_fk")
	private Unit unit;
	
	@JsonFormat(pattern="MMM dd yyyy")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	private String descriptionOfMalfunction;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="requestBy_fk")
	private User requestBy;
	
	private String recommendation;
		
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="recommendedBy_fk")
	private User recommendedBy;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="inspectedBy_fk")
	private User inspectedBy;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="notedBy_fk")
	private User notedBy;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="inHouseRepairedBy_fk")
	private User inHouseRepairedBy;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="inHouseReceivedBy_fk")
	private User inHouseReceivedBy;
	
	private Boolean isApproved;

	@JsonIgnore
	private Long yearReport;

	public Request() {
		super();
		unit = new Unit();
		date = new Date();
		recommendedBy = new User();
		inspectedBy = new User();
		notedBy = new User();
		inHouseRepairedBy = new User();
		inHouseReceivedBy = new User();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescriptionOfMalfunction() {
		return descriptionOfMalfunction;
	}

	public void setDescriptionOfMalfunction(String descriptionOfMalfunction) {
		this.descriptionOfMalfunction = descriptionOfMalfunction;
	}



	public User getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(User requestBy) {
		this.requestBy = requestBy;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public User getRecommendedBy() {
		return recommendedBy;
	}

	public void setRecommendedBy(User recommendedBy) {
		this.recommendedBy = recommendedBy;
	}

	public User getInspectedBy() {
		return inspectedBy;
	}

	public void setInspectedBy(User inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public User getNotedBy() {
		return notedBy;
	}

	public void setNotedBy(User notedBy) {
		this.notedBy = notedBy;
	}

	public User getInHouseRepairedBy() {
		return inHouseRepairedBy;
	}

	public void setInHouseRepairedBy(User inHouseRepairedBy) {
		this.inHouseRepairedBy = inHouseRepairedBy;
	}

	public User getInHouseReceivedBy() {
		return inHouseReceivedBy;
	}

	public void setInHouseReceivedBy(User inHouseReceivedBy) {
		this.inHouseReceivedBy = inHouseReceivedBy;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	
	public Long getYearReport() {
		return yearReport;
	}

	public void setYearReport(Long yearReport) {
		this.yearReport = yearReport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", unit=" + unit + ", date=" + date + ", descriptionOfMalfunction="
				+ descriptionOfMalfunction + ", requestBy=" + requestBy + ", recommendation=" + recommendation
				+ ", recommendedBy=" + recommendedBy + ", inspectedBy=" + inspectedBy + ", notedBy=" + notedBy
				+ ", inHouseRepairedBy=" + inHouseRepairedBy + ", inHouseReceivedBy=" + inHouseReceivedBy
				+ ", isApproved=" + isApproved + "]";
	}


	
}
