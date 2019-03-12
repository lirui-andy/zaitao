package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the y_ztry database table.
 * 
 */
@Entity
@Table(name="y_ztry")
@NamedQuery(name="YZtry.findAll", query="SELECT y FROM YZtry y")
public class YZtry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String ztrybh;

	private String asjbh;

	private String bmch;

	private String csrq;

	private String cyzj;

	private String hjdz;

	@Column(name="hjdz_dzmc")
	private String hjdzDzmc;

	@Column(name="ladw_gajgjgdm")
	private String ladwGajgjgdm;

	private String mzdm;

	private String sfzh;

	private String sgxx;

	private String tjjbdm;

	@Column(name="tpfx_jyqk")
	private String tpfxJyqk;

	private String tpsj;

	private String xbdm;

	private String xm;

	private String xzqhdm;

	@Column(name="xzz_dzmc")
	private String xzzDzmc;

	@Column(name="xzz_xzqhdm")
	private String xzzXzqhdm;

	@Column(name="zbr_lxdh")
	private String zbrLxdh;

	@Column(name="zbr_xm")
	private String zbrXm;

	private String ztjj;

	private String ztrylxdm;

	public YZtry() {
	}

	public String getZtrybh() {
		return this.ztrybh;
	}

	public void setZtrybh(String ztrybh) {
		this.ztrybh = ztrybh;
	}

	public String getAsjbh() {
		return this.asjbh;
	}

	public void setAsjbh(String asjbh) {
		this.asjbh = asjbh;
	}

	public String getBmch() {
		return this.bmch;
	}

	public void setBmch(String bmch) {
		this.bmch = bmch;
	}

	public String getCsrq() {
		return this.csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getCyzj() {
		return this.cyzj;
	}

	public void setCyzj(String cyzj) {
		this.cyzj = cyzj;
	}

	public String getHjdz() {
		return this.hjdz;
	}

	public void setHjdz(String hjdz) {
		this.hjdz = hjdz;
	}

	public String getHjdzDzmc() {
		return this.hjdzDzmc;
	}

	public void setHjdzDzmc(String hjdzDzmc) {
		this.hjdzDzmc = hjdzDzmc;
	}

	public String getLadwGajgjgdm() {
		return this.ladwGajgjgdm;
	}

	public void setLadwGajgjgdm(String ladwGajgjgdm) {
		this.ladwGajgjgdm = ladwGajgjgdm;
	}

	public String getMzdm() {
		return this.mzdm;
	}

	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getSgxx() {
		return this.sgxx;
	}

	public void setSgxx(String sgxx) {
		this.sgxx = sgxx;
	}

	public String getTjjbdm() {
		return this.tjjbdm;
	}

	public void setTjjbdm(String tjjbdm) {
		this.tjjbdm = tjjbdm;
	}

	public String getTpfxJyqk() {
		return this.tpfxJyqk;
	}

	public void setTpfxJyqk(String tpfxJyqk) {
		this.tpfxJyqk = tpfxJyqk;
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

	public String getXzqhdm() {
		return this.xzqhdm;
	}

	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}

	public String getXzzDzmc() {
		return this.xzzDzmc;
	}

	public void setXzzDzmc(String xzzDzmc) {
		this.xzzDzmc = xzzDzmc;
	}

	public String getXzzXzqhdm() {
		return this.xzzXzqhdm;
	}

	public void setXzzXzqhdm(String xzzXzqhdm) {
		this.xzzXzqhdm = xzzXzqhdm;
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

	public String getZtjj() {
		return this.ztjj;
	}

	public void setZtjj(String ztjj) {
		this.ztjj = ztjj;
	}

	public String getZtrylxdm() {
		return this.ztrylxdm;
	}

	public void setZtrylxdm(String ztrylxdm) {
		this.ztrylxdm = ztrylxdm;
	}

}