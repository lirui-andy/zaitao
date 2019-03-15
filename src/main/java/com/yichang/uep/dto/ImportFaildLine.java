package com.yichang.uep.dto;

import java.io.Serializable;

public class ImportFaildLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int line;
	String reason;
	String rawData;
	
	public ImportFaildLine(int line, String reason) {
		super();
		this.line = line;
		this.reason = reason;
	}

	public ImportFaildLine(int line, String reason, String rawData) {
		super();
		this.line = line;
		this.reason = reason;
		this.rawData = rawData;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	
}
