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
	private int impCrewId;

	@Column(name="crew_id")
	private String crewId;

	@Column(name="crew_status")
	private String crewStatus;

	private String crewname;

	@Column(name="end_date")
	private String endDate;

	@Column(name="id_card")
	private String idCard;

	@Temporal(TemporalType.TIMESTAMP)
	private Date imptime;

	private String impuser;

	@Column(name="onship_duty")
	private String onshipDuty;

	private String opertime;

	private String operuser;

	private String remark;

	@Column(name="ship_crew_id")
	private String shipCrewId;

	@Column(name="ship_id")
	private String shipId;

	@Column(name="start_date")
	private String startDate;

	public YImpCrew() {
	}

	public int getImpCrewId() {
		return this.impCrewId;
	}

	public void setImpCrewId(int impCrewId) {
		this.impCrewId = impCrewId;
	}

	public String getCrewId() {
		return this.crewId;
	}

	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}

	public String getCrewStatus() {
		return this.crewStatus;
	}

	public void setCrewStatus(String crewStatus) {
		this.crewStatus = crewStatus;
	}

	public String getCrewname() {
		return this.crewname;
	}

	public void setCrewname(String crewname) {
		this.crewname = crewname;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
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

	public String getOpertime() {
		return this.opertime;
	}

	public void setOpertime(String opertime) {
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

	public String getShipCrewId() {
		return this.shipCrewId;
	}

	public void setShipCrewId(String shipCrewId) {
		this.shipCrewId = shipCrewId;
	}

	public String getShipId() {
		return this.shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}