package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.Bus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusMapper {
    // 根据license查询车辆信息
    List<Bus> findBylicense(String license);

    List<Bus> findBypage(int page);

    // 新增车辆信息
    int insert(Bus Bus);

    // 更新车辆信息
    int update(Bus Bus);

    // 删除车辆信息
    int delete(String license);

    int isExist(String license);

    int total_bus();
}
