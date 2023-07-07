package com.wk.warehouse.entity;

/**
 * 用户实体类
 */
public class User {
	private int isAdmin; //用户类型 0为普通用户 1为管理员 2为超管
	private String idCard; //身份证
	private String password; //密码
	private String userName; //姓名
	private String email; //电子邮件
	private String phoneNumber;//电话号码

	public int getisAdmin() {return isAdmin;}
	public void setisAdmin(int isAdmin) { this.isAdmin = isAdmin; }

	public String getidCard() { return idCard; }
	public void setidCard(String idCard) { this.idCard = idCard; }

	public String getpassword() { return password; }
	public void setpassword(String password) { this.password = password; }

	public String getuserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	public String getemail() { return email; }
	public void setemail(String email) { this.email = email; }

	public String getphoneNumber() { return phoneNumber; }
	public void setphoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

}
