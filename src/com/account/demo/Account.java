package com.account.demo;

import java.util.Date;

public class Account {
	private String accName;
	private Integer accNum;
	private String accState;
	private Date registerTime;
	private Date lastLoginTime;
	private String accType;
	private String remark;
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Integer getAccNum() {
		return accNum;
	}
	public void setAccNum(Integer accNum) {
		this.accNum = accNum;
	}
	public String getAccState() {
		return accState;
	}
	public void setAccState(String accState) {
		this.accState = accState;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
