package com.wk.warehouse.entity;
import java.util.List;
public class PassengerReturnList {
    private int status;
    private List<PassengerInfo> passengerList;//与PassengerInfoReturnData不同的是进行了List处理

    public PassengerReturnList(int status, List<PassengerInfo> passengerList){
        this.status = status;
        this.passengerList = passengerList;
    }

    public int getStatus() {return status;}
    public void setStatus(int status) {this.status = status;}

    public List<PassengerInfo> getPassengerList(){return passengerList;}
    public void setPassengerList(List<PassengerInfo> passengerList){this.passengerList = passengerList;}
}
