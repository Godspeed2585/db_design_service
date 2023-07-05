package com.example.db_design_service.bean;

import java.util.List;

public class GetAllUserReturnData {
    private int status;
    private List<UserInfo> userList; //对User进行List处理

    public GetAllUserReturnData(int status, List<UserInfo> userList) {
        this.status = status;
        this.userList = userList;
    }

    public int getStatus() {return status;}
    public void setStatus(int status) {this.status = status;}

    public List<UserInfo> getUserList() {return userList;}
    public void setUserList(List<UserInfo> userList) {this.userList = userList;}
}
