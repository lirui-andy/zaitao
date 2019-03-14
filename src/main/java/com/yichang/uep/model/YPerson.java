package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the y_person database table.
 * 
 */
@Entity
@Table(name="y_person")
@NamedQuery(name="YPerson.findAll", query="SELECT y FROM YPerson y")
public class YPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="person_id")
	private int personId;

	@Column(name="id_card")
	private String idCard;

	@Temporal(TemporalType.TIMESTAMP)
	private Date imptime;

	private String impuser;

	private String name;

	public YPerson() {
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}