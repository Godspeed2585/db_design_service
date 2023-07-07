package com.wk.warehouse.service.impl;

import com.wk.warehouse.entity.Order;
import com.wk.warehouse.mapper.OrderMapper;
import com.wk.warehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service实现类
 */

@Service
public class OrderServiceImpl implements OrderService {
    // 注入Mapper对象
    @Autowired
    private OrderMapper OrderMapper;

    @Override
    public List<Order> findByorderId(int orderId) {
        return OrderMapper.findByorderId(orderId);
    }

    @Override
    public List<Order> findBywagonId(String wagonId) { return OrderMapper.findBywagonId(wagonId); };

    @Override
    public int insert(Order order) {
        return OrderMapper.insert(order);
    }

    @Override
    public int update(Order order) {
        return OrderMapper.update(order);
    }

    @Override
    public int delete(int orderId) { return OrderMapper.delete(orderId); }

    public int isExist(int orderId) {
        return OrderMapper.isExist(orderId);
    }

    public int total_findByorderId(int orderId) { return OrderMapper.total_findByorderId(orderId); }
}
