package com.example.db_design_service.bean;
//司机信息
import java.sql.Date;
public class Driver {
    public String driverName;//司机姓名
    public Date entryTime;//司机入职时间
    public String gender;//司机性别
    public int jobId;//司机工号
    
    public Driver(String driverName, Date entryTime, String gender, int jobId)
    {
        this.driverName = driverName;
        this.entryTime = entryTime;
        this.gender = gender;
        this.jobId =jobId;
    }

    public String getdriverName() {return driverName;}
    public void setdriverName(String driverName) {this.driverName = driverName;}

    public Date getentryTime() {return entryTime;}
    public void setentryTime(Date entryTime) {this.entryTime = entryTime;}

    public String getgender() {return gender;}
    public void setgender(String gender) {this.gender = gender;}

    public int getjobId() {return jobId;}
    public void setjobId(int jobId) {this.jobId = jobId;}
}