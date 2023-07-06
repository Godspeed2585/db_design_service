package com.wk.warehouse.entity;
//作为给前端的查询乘客信息的返回类

public class PassengerInfoReturnData {
    private int status;
    private PassengerInfo passengerInfos;

    public PassengerInfoReturnData(int status, PassengerInfo passengerInfos) {
        this.status = status;
        this.passengerInfos = passengerInfos;
    }

    public PassengerInfo getPassengerInfos() { return passengerInfos; }
    public void setPassengerInfos(PassengerInfo passengerInfos) { this.passengerInfos = passengerInfos; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
