package com.example.db_design_service.bean;

//使用phoneNumber与password进行登录

public class UserLogin {
    private String phoneNumber;//电话号码
    private String password;//密码

    public String getpassword() {return password;}

    public void setpassword(String password) {this.password = password;}

    public String getphoneNumber() {return phoneNumber;}

    public void setphoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
