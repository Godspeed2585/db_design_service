package com.wk.warehouse.entity;

public class DriverInfoReturnData {
    private int status;
    private DriverInfo driverInfos;
    
    public DriverInfoReturnData(int status, DriverInfo driverInfos)
    {
        this.status = status;
        this.driverInfos = driverInfos;
    }

    public DriverInfo getDriverInfos(){return driverInfos;}
    public void setDriverInfos(DriverInfo driverInfos){this.driverInfos = driverInfos;}

    public int getstatus(){return status;}
    public void setstatus(int status){this.status = status;}
}