package com.wk.warehouse.entity;

//用户登录部分

public class UserLogin {
    public String phoneNumber;//电话号码
    public String password;//密码
    public int isAdmin;//用户身份
    public String verificationCode;
    public String verificationKey; //验证码环节

    public String getphoneNumber() {return phoneNumber;}
    public void setphoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getpassword() {return password;}
    public void setpassword(String password) {this.password = password;}
    
    public int getisAdmin() {return isAdmin;}
    public void setisAdmin(int isAdmin) {this.isAdmin = isAdmin;}

    public String getverificationCode() {return verificationCode;}
    public void setverificationCode(String verificationCode) {this.verificationCode = verificationCode;}

    public String getverificationKey() {return verificationKey;}
    public void setverificationKey(String verificationKey) {this.verificationKey = verificationKey;}

}
