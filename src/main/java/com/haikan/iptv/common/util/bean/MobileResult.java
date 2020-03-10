package com.haikan.iptv.common.util.bean;

import java.io.Serializable;

public class MobileResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535893410261949100L;
	
	private String code = "200" ;
	private String errorMessage ;
	
	private Object results ;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
