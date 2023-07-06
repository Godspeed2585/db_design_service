package com.wk.warehouse.entity;
/**
 * 前端的返回类
 * 根据后台对信息的处理情况 返回状态码以及提示信息
 * 1为成功,其他为失败，并对应不同的状态
*/
public class RespBean {
    private int status;
    private String success;

    public RespBean(int status, String success) {
        this.status = status;
        this.success  =success;
    }

    public int getStatus() {return status;}
    public void setStatus(int status) {this.status = status;}

    public String getSuccess() {return success;}
    public void setSuccess(String success) {this.success = success;}
}
