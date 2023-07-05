package com.example.db_design_service.bean;

//登录部分

public class UserLogin {
    private String phoneNumber;//电话号码
    private String password;//密码
    private int isAdmin;

    public String getpassword() {return password;}

    public void setpassword(String password) {this.password = password;}

    public String getphoneNumber() {return phoneNumber;}

    public void setphoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public int getisAdmin() {return isAdmin;}

    public void setisAdmin(int isAdmin) {this.isAdmin = isAdmin;}

}
