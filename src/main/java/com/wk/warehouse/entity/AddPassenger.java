package com.wk.warehouse.entity;

public class AddPassenger {
    public String phoneNumber;
    public String idCard;

    public AddPassenger(String phoneNumber, String idCard)
    {
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
    }

    public String getphoneNumber() {return phoneNumber;}
    public void setphoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    
    public String getidCard() {return idCard;}
    public void setidCard(String idCard) {this.idCard = idCard;}
}
