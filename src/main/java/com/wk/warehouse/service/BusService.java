package com.wk.warehouse.service;

import com.wk.warehouse.entity.Bus;

import java.util.List;

public interface BusService {
    List<Bus> findBylicense(String license);

    int insert(Bus bus);

    int update(Bus bus);

    int delete(String license);
    int isExist(String license);
}
