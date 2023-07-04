package com.example.db_design_service.bean;

public class UserInfo {
    public boolean isAdmin;//用于区分是否管理员
    private String idCard;//身份证号
    private String password;//密码
    private String userName;//用户名
    private String email;//邮箱
    private String phoneNumber;//电话号码

    public UserInfo(boolean isAdmin,String idCard,String password,
                String userName,String email,String phoneNumber)
    {
        this.isAdmin = isAdmin;
        this.idCard = idCard;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean getisAdmin() {return isAdmin;}
    public void setisAdmin(boolean isAdmin) {this.isAdmin = isAdmin;}

    public String getidCard() {return idCard;}
    public void setidCard(String idCard) {this.idCard = idCard;}

    public String getpassword() {return password;}
    public void setpassword(String password) {this.password = password;}

    public String getuserName() {return userName;}
    public void setuserName(String userName) {this.userName = userName;}

    public String getemail() {return email;}
    public void setemail(String email) {this.email = email;}

    public String getphoneNumber() {return phoneNumber;}
    public void setphoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
