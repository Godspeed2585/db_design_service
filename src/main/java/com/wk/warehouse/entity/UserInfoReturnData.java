package com.wk.warehouse.entity;

public class UserInfoReturnData {
   private int status;
   private UserInfo userInfo;

    public UserInfoReturnData(int status, UserInfo userInfo){
        this.status = status;
        this.userInfo = userInfo;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public UserInfo getuserInfo() { return userInfo; }
    public void setuserInfo(UserInfo userInfo) { this.userInfo = userInfo; }
}
