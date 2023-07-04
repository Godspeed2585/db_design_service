package com.example.db_design_service.bean;
/*
 * Token类  
 * 作为服务端 标记用户的唯一标示,将token存入缓存标记用户
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
