package com.example.db_design_service.bean;
import java.util.List;
public class GetAllDriverReturnData {
    private int status;
    private List<Driver> driverList;

    public GetAllDriverReturnData(int status, List<Driver> driverList) {
        this.status = status;
        this.driverList = driverList;
    }

    public int getStatus() {return status;}
    public void setStatus(int status) {this.status = status;}

    public List<Driver> getDriverList() {return driverList;}
    public void setDriverList(List<Driver> driverList) {this.driverList = driverList;}
}
