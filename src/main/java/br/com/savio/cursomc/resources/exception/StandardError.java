package br.com.savio.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeStamped;
	
	
	public StandardError() {
		super();
	}
	public StandardError(Integer status, String msg, Long timeStamped) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamped = timeStamped;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimeStamped() {
		return timeStamped;
	}
	public void setTimeStamped(Long timeStamped) {
		this.timeStamped = timeStamped;
	}
	
}
