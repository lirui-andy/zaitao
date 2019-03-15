package com.yichang.uep.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the y_compare_batch database table.
 * 
 */
@Entity
@Table(name="y_compare_batch")
@NamedQuery(name="YCompareBatch.findAll", query="SELECT y FROM YCompareBatch y")
public class YCompareBatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="batch_id")
	private int batchId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="compare_time")
	private Date compareTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;

	@Column(name="matched_count")
	private int matchedCount;

	@Column(name="ztry_count")
	private int ztryCount;

	public YCompareBatch() {
	}

	public int getBatchId() {
		return this.batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public Date getCompareTime() {
		return this.compareTime;
	}

	public void setCompareTime(Date compareTime) {
		this.compareTime = compareTime;
	}

	public int getMatchedCount() {
		return this.matchedCount;
	}

	public void setMatchedCount(int matchedCount) {
		this.matchedCount = matchedCount;
	}

	public int getZtryCount() {
		return this.ztryCount;
	}

	public void setZtryCount(int ztryCount) {
		this.ztryCount = ztryCount;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	

}