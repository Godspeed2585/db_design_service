package com.example.db_design_service.bean;
/*
 * Token类,与RedisUtils配合使用
 * 作为服务端标记用户的唯一标识,将token存入缓存中标记用户
 * (一般来讲,Token存储的是已登录的用户的信息)
*/
public class Token {
    private  String token;

    public Token(String token)
    {
        this.token = token;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
}
