package gov.dost.region12.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name="Preventive_Maintenance_Schedule")
public class PreventiveMaintenance implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="unit_fk")
	private Unit unit;

	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="prepared_by_fk")
	private User preparedBy;
	
	@JsonFormat(pattern="MMM dd yyyy")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date preparedDate;

	@OneToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="approved_by_fk")
	private User approvedBy;
	
	@JsonIgnore
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date approvedDate;
	
	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="checkAll", joinColumns=@JoinColumn(name="preventiveMaintenance_id"))
	@Column(name="checkAll")
	private List<String> checkAll = new ArrayList<String>();

/*	@JsonIgnore
	private List<String> check1 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check2 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check3 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check4 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check5 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check6 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check7 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check8 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check9 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check10 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check11 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check12 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check13 = new ArrayList<String>();

	@JsonIgnore
	private List<String> check14 = new ArrayList<String>();
*/

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

	public User getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(User preparedBy) {
		this.preparedBy = preparedBy;
	}

	public Date getPreparedDate() {
		return preparedDate;
	}

	public void setPreparedDate(Date preparedDate) {
		this.preparedDate = preparedDate;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public List<String> getCheckAll() {
		return checkAll;
	}

	public void setCheckAll(List<String> checkAll) {
		this.checkAll = checkAll;
	}

	/*public List<String> getCheck1() {
		return check1;
	}

	public void setCheck1(List<String> check1) {
		this.check1 = check1;
	}

	public List<String> getCheck2() {
		return check2;
	}

	public void setCheck2(List<String> check2) {
		this.check2 = check2;
	}

	public List<String> getCheck3() {
		return check3;
	}

	public void setCheck3(List<String> check3) {
		this.check3 = check3;
	}

	public List<String> getCheck4() {
		return check4;
	}

	public void setCheck4(List<String> check4) {
		this.check4 = check4;
	}

	public List<String> getCheck5() {
		return check5;
	}

	public void setCheck5(List<String> check5) {
		this.check5 = check5;
	}

	public List<String> getCheck6() {
		return check6;
	}

	public void setCheck6(List<String> check6) {
		this.check6 = check6;
	}

	public List<String> getCheck7() {
		return check7;
	}

	public void setCheck7(List<String> check7) {
		this.check7 = check7;
	}

	public List<String> getCheck8() {
		return check8;
	}

	public void setCheck8(List<String> check8) {
		this.check8 = check8;
	}

	public List<String> getCheck9() {
		return check9;
	}

	public void setCheck9(List<String> check9) {
		this.check9 = check9;
	}

	public List<String> getCheck10() {
		return check10;
	}

	public void setCheck10(List<String> check10) {
		this.check10 = check10;
	}

	public List<String> getCheck11() {
		return check11;
	}

	public void setCheck11(List<String> check11) {
		this.check11 = check11;
	}

	public List<String> getCheck12() {
		return check12;
	}

	public void setCheck12(List<String> check12) {
		this.check12 = check12;
	}

	public List<String> getCheck13() {
		return check13;
	}

	public void setCheck13(List<String> check13) {
		this.check13 = check13;
	}

	public List<String> getCheck14() {
		return check14;
	}

	public void setCheck14(List<String> check14) {
		this.check14 = check14;
	}
*/

	@Override
	public String toString() {
		return "PreventiveMaintenance [id=" + id + ", unit=" + unit + ", preparedBy=" + preparedBy + ", preparedDate="
				+ preparedDate + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + ((approvedDate == null) ? 0 : approvedDate.hashCode());
		result = prime * result + ((checkAll == null) ? 0 : checkAll.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((preparedBy == null) ? 0 : preparedBy.hashCode());
		result = prime * result + ((preparedDate == null) ? 0 : preparedDate.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		PreventiveMaintenance other = (PreventiveMaintenance) obj;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (approvedDate == null) {
			if (other.approvedDate != null)
				return false;
		} else if (!approvedDate.equals(other.approvedDate))
			return false;
		if (checkAll == null) {
			if (other.checkAll != null)
				return false;
		} else if (!checkAll.equals(other.checkAll))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (preparedBy == null) {
			if (other.preparedBy != null)
				return false;
		} else if (!preparedBy.equals(other.preparedBy))
			return false;
		if (preparedDate == null) {
			if (other.preparedDate != null)
				return false;
		} else if (!preparedDate.equals(other.preparedDate))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}




	
}
