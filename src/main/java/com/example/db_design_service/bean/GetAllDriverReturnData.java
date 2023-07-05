package com.example.db_design_service.bean;
import java.util.List;
public class GetAllDriverReturnData {
    private int status;
    private List<DriverInfo> driverList;//这里与DriverInfoReturnData不同,进行了List处理

    public GetAllDriverReturnData(int status, List<DriverInfo> driverList) {
        this.status = status;
        this.driverList = driverList;
    }

    public int getStatus() {return status;}
    public void setStatus(int status) {this.status = status;}

    public List<DriverInfo> getDriverList() {return driverList;}
    public void setDriverList(List<DriverInfo> driverList) {this.driverList = driverList;}
}
