package com.wk.warehouse.util;
//当前登录的用户
public class CurrentUser {
    private int isAdmin; //用户类型
    private String phoneNumber;//电话号码
	private String password; //密码

    public CurrentUser(int isAdmin, String phoneNumber, String password)
    {
        this.isAdmin =isAdmin;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public int getisAdmin() {return isAdmin;}
	public void setisAdmin(int isAdmin) { this.isAdmin = isAdmin; }

    public String getphoneNumber() { return phoneNumber; }
	public void setphoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getpassword() { return password; }
	public void setpassword(String password) { this.password = password; }
}
