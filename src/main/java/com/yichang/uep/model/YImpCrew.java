package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the y_imp_crew database table.
 * 
 */
@Entity
@Table(name="y_imp_crew")
@NamedQuery(name="YImpCrew.findAll", query="SELECT y FROM YImpCrew y")
public class YImpCrew implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="imp_crew_id")
	private Integer impCrewId;

	@Column(name="crew_id")
	private Integer crewId;

	@Column(name="crew_status")
	private Integer crewStatus;

	private String crewname;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="id_card")
	private String idCard;

	@Temporal(TemporalType.TIMESTAMP)
	private Date imptime;

	private String impuser;

	@Column(name="onship_duty")
	private String onshipDuty;

	@Temporal(TemporalType.TIMESTAMP)
	private Date opertime;

	private String operuser;

	private String remark;

	@Column(name="ship_crew_id")
	private Integer shipCrewId;

	@Column(name="ship_id")
	private Integer shipId;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	public YImpCrew() {
	}

	public Integer getImpCrewId() {
		return this.impCrewId;
	}

	public void setImpCrewId(Integer impCrewId) {
		this.impCrewId = impCrewId;
	}

	public Integer getCrewId() {
		return this.crewId;
	}

	public void setCrewId(Integer crewId) {
		this.crewId = crewId;
	}

	public Integer getCrewStatus() {
		return this.crewStatus;
	}

	public void setCrewStatus(Integer crewStatus) {
		this.crewStatus = crewStatus;
	}

	public String getCrewname() {
		return this.crewname;
	}

	public void setCrewname(String crewname) {
		this.crewname = crewname;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getImptime() {
		return this.imptime;
	}

	public void setImptime(Date imptime) {
		this.imptime = imptime;
	}

	public String getImpuser() {
		return this.impuser;
	}

	public void setImpuser(String impuser) {
		this.impuser = impuser;
	}

	public String getOnshipDuty() {
		return this.onshipDuty;
	}

	public void setOnshipDuty(String onshipDuty) {
		this.onshipDuty = onshipDuty;
	}

	public Date getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public String getOperuser() {
		return this.operuser;
	}

	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getShipCrewId() {
		return this.shipCrewId;
	}

	public void setShipCrewId(Integer shipCrewId) {
		this.shipCrewId = shipCrewId;
	}

	public Integer getShipId() {
		return this.shipId;
	}

	public void setShipId(Integer shipId) {
		this.shipId = shipId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}