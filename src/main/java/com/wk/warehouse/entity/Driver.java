package com.wk.warehouse.entity;

import java.util.Date;

/**
 * 司机实体类
 */
public class Driver {
	private String driverName;//司机姓名
	private Date entryTime;//入职时间
	private String gender; //性别
	private int jobId; //工号

	public String getdriverName() {return driverName;}
	public void setdriverName(String driverName) { this.driverName = driverName; }

	public Date getentryTime() { return entryTime; }
	public void setentryTime(Date entryTime) { this.entryTime = entryTime; }

	public String gender() { return gender; }
	public void setgender(String gender) { this.gender = gender; }

	public int getjobId() { return jobId; }
	public void setjobId(int jobId) { this.jobId = jobId; }

}
