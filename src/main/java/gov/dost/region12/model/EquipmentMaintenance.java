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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Equipment_Maintenance_Record")
public class EquipmentMaintenance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3581119558148847214L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="request_fk")
	private Request request;
	
	private String operationPerformed;
	
	@JsonFormat(pattern="MMM dd yyyy")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="performed_repair_by_fk")
	private User performedBy;
	
	private String remark;

	private Long yearReport;
	
	@Transient
	private Long unitId;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public String getOperationPerformed() {
		return operationPerformed;
	}

	public void setOperationPerformed(String operationPerformed) {
		this.operationPerformed = operationPerformed;
	}

	public User getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(User performedBy) {
		this.performedBy = performedBy;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getYearReport() {
		return yearReport;
	}

	public void setYearReport(Long yearReport) {
		this.yearReport = yearReport;
	}

	public Long getUnitId() {
		if(request != null)
			if(request.getUnit() != null)
				return request.getUnit().getId();
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
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
		EquipmentMaintenance other = (EquipmentMaintenance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EquipmentMaintenance [id=" + id + ", request=" + request + ", date=" + date + ", operationPerformed="
				+ operationPerformed + ", performedBy=" + performedBy + "]";
	}
	
	
}
