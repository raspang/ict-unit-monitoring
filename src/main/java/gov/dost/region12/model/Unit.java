package gov.dost.region12.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="UNIT")
public class Unit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9012816969074217166L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String equipmentName;
	
	private String codeNo;
	
	private String modelNo;
	private String serialNo;
	private String otherComponent;
	
	@JsonFormat(pattern="MMM dd yyyy")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateAcquired;
	
	private String location;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="receivedBy_fk")
	private User receivedBy;

	@JsonFormat(pattern="MMM dd yyyy")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateRecieved;
	
	@Transient
	private String displayUnit;
	
	
	public Unit() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOtherComponent() {
		return otherComponent;
	}
	public void setOtherComponent(String otherComponent) {
		this.otherComponent = otherComponent;
	}
	
	public User getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(User receivedBy) {
		this.receivedBy = receivedBy;
	}
	public Date getDateRecieved() {
		return dateRecieved;
	}
	public void setDateRecieved(Date dateRecieved) {
		this.dateRecieved = dateRecieved;
	}
	public Date getDateAcquired() {
		return dateAcquired;
	}
	public void setDateAcquired(Date dateAcquired) {
		this.dateAcquired = dateAcquired;
	}

	public String getDisplayUnit() {
		String unit = equipmentName.concat(" (").concat(modelNo).concat(")").concat("\n").concat(serialNo);
		return unit;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((serialNo == null) ? 0 : serialNo.hashCode());
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
		Unit other = (Unit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Unit [id=" + id + ", equipmentName=" + equipmentName + ", codeNo=" + codeNo + ", modelNo=" + modelNo
				+ ", serialNo=" + serialNo + ", otherComponent=" + otherComponent + ", dateAcquired=" + dateAcquired
				+ ", location=" + location + ", receivedBy=" + receivedBy + ", dateRecieved=" + dateRecieved + "]";
	}

	
	
	
}
