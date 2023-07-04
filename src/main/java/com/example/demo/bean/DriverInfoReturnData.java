package com.example.db_design_service.bean;

public class DriverInfoReturnData {
    private int status;
    private Driver driverInfos;
    
    public DriverInfoReturnData(int status, Driver driverInfos)
    {
        this.status = status;
        this.driverInfos = driverInfos;
    }

    public Driver getDriverInfos(){return driverInfos;}
    public void setDriverInfos(Driver driverInfos){this.driverInfos = driverInfos;}

    public int getstatus(){return status;}
    public void setstatus(int status){this.status = status;}
}