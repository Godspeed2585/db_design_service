package com.example.db_design_service.bean;

public class PassengerInfo {
    public String passengerName;//乘客姓名
    public String passengerPhoneNumber;//乘客电话号码
    public int age;//乘客年龄
    public String idCard;//乘客身份证

    public PassengerInfo(String passengerName, String passengerPhoneNumber, int age, String idCard)
    {
        this.passengerName = passengerName;
        this.passengerPhoneNumber = passengerPhoneNumber;
        this.age = age;
        this.idCard = idCard;
    }

    public String getpassengerName() {return passengerName;}
    public void setpassengerName(String passengerName) {this.passengerName = passengerName;}

    public String getpassengerPhoneNumber() {return passengerPhoneNumber;}
    public void setpassengerPhoneNumber(String passengerPhoneNumber) {this.passengerPhoneNumber = passengerPhoneNumber;}

    public int getage() {return age;}
    public void setage(int age) {this.age = age;}

    public String getidCard(){return idCard;}
    public void setidCard(String idCard) {this.idCard = idCard;}
}
