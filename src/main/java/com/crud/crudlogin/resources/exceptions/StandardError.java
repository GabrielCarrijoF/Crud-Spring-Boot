package com.crud.crudlogin.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Instant timesStamp;
	private Integer status;
	private String error;
	private String menssage;
	private String path;
	
	public StandardError() {
		
	}

	public Instant getTimesStamp() {
		return timesStamp;
	}

	public void setTimesStamp(Instant timesStamp) {
		this.timesStamp = timesStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMenssage() {
		return menssage;
	}

	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	 
	
}
