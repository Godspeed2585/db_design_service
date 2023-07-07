package com.wk.warehouse.util;

import org.apache.ibatis.annotations.Select;

public interface TokenDao {

    /**
     * 根据用户phoneNumber查询password
     */
    @Select("SELECT password FROM \"TicketManagement\".user WHERE\"phoneNumber\"=#{phoneNumber}")
    public String findpasswordByphoneNumber(String phoneNumber);

}