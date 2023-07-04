package com.example.db_design_service.bean;
//乘客信息
public class Passenger {
    public String passengerName;//乘客姓名
    private String passengerPhoneNumber;//乘客电话号码
    public int age;//乘客年龄
    private String idCard;//乘客身份证

    public Passenger(String passengerName, String passengerPhoneNumber, int age, String idCard)
    {
        this.passengerName = passengerName;
        this.passengerPhoneNumber = passengerPhoneNumber;
        this.age = age;
        this.idCard = idCard;
    }

    public String getpassengerName() {return passengerName;}
    public void setpassengerName(String passengerName) {this.passengerName = passengerName;}

    public int getage() {return age;}
    public void setage(int age) {this.age = age;}

    public String getpassengerPhoneNumber() {return passengerPhoneNumber;}
    public void setpassengerPhoneNumber(String passengerPhoneNumber) {this.passengerPhoneNumber = passengerPhoneNumber;}

    public String getidCard(){return idCard;}
    public void setidCard(String idCard) {this.idCard = idCard;}
}
