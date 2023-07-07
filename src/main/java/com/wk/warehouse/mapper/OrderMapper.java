package com.wk.warehouse.mapper;

import com.wk.warehouse.entity.Bus;
import com.wk.warehouse.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    // 根据orderId查询order信息
    List<Order> findByorderId(int orderId);

    List<Order> findBywagonId(String wagonId);

    List<Order> findBypage(int page);

    // 新增order信息
    int insert(Order order);

    // 更新车辆信息
    int update(Order order);

    // 删除车辆信息
    int delete(int orderId);

    int isExist(int orderId);

    int total_order();
}
