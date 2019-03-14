package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the y_compare_detail database table.
 * 
 */
@Entity
@Table(name="y_compare_detail")
@NamedQuery(name="YCompareDetail.findAll", query="SELECT y FROM YCompareDetail y")
public class YCompareDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="compare_detail_id")
	private int compareDetailId;

	@Column(name="batch_id")
	private int batchId;

	@Column(name="person_id")
	private int personId;

	private String sfzh;

	private String tpsj;

	private String xbdm;

	private String xm;

	@Column(name="xzz_dzmc")
	private String xzzDzmc;

	@Column(name="zbr_lxdh")
	private String zbrLxdh;

	@Column(name="zbr_xm")
	private String zbrXm;

	private String ztrybh;

	public YCompareDetail() {
	}

	public int getCompareDetailId() {
		return this.compareDetailId;
	}

	public void setCompareDetailId(int compareDetailId) {
		this.compareDetailId = compareDetailId;
	}

	public int getBatchId() {
		return this.batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getTpsj() {
		return this.tpsj;
	}

	public void setTpsj(String tpsj) {
		this.tpsj = tpsj;
	}

	public String getXbdm() {
		return this.xbdm;
	}

	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXzzDzmc() {
		return this.xzzDzmc;
	}

	public void setXzzDzmc(String xzzDzmc) {
		this.xzzDzmc = xzzDzmc;
	}

	public String getZbrLxdh() {
		return this.zbrLxdh;
	}

	public void setZbrLxdh(String zbrLxdh) {
		this.zbrLxdh = zbrLxdh;
	}

	public String getZbrXm() {
		return this.zbrXm;
	}

	public void setZbrXm(String zbrXm) {
		this.zbrXm = zbrXm;
	}

	public String getZtrybh() {
		return this.ztrybh;
	}

	public void setZtrybh(String ztrybh) {
		this.ztrybh = ztrybh;
	}

}