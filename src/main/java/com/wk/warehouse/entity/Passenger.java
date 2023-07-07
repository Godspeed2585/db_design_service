package com.wk.warehouse.entity;
/**
 * 乘客实体类
 */
public class Passenger {
	private String passengerName; //乘客姓名
	private String passengerPhoneNumber; //乘客电话
	private int age; //年龄
	private String idCard; //身份证
	
	public String getpassengerName() { return passengerName; }
	public void setpassengerName(String passengerName) { this.passengerName = passengerName; }

    public String getpassengerPhoneNumber() { return passengerPhoneNumber; }
	public void setpassengerPhoneNumber(String passengerPhoneNumber) { this.passengerPhoneNumber = passengerPhoneNumber; }

    public int getage() {return age;}
	public void setage(int age) { this.age = age; }

    public String getidCard() { return idCard; }
	public void setidCard(String idCard) { this.idCard = idCard; }
}
