package com.yichang.uep.dto;

import java.io.Serializable;

public class MatchBatchQueryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int batchId;
	//任务日期范围
	String dateRange;
	//是否只查询有比中结果的
	boolean matchedOnly;
	
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public boolean isMatchedOnly() {
		return matchedOnly;
	}
	public void setMatchedOnly(boolean matchedOnly) {
		this.matchedOnly = matchedOnly;
	}
	
}
