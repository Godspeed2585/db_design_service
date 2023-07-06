package com.wk.warehouse;

import com.wk.warehouse.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wk.warehouse.entity.Bus;
import com.wk.warehouse.mapper.BusMapper;
import com.wk.warehouse.entity.Order;
import com.wk.warehouse.mapper.OrderMapper;
import java.util.List;

@SpringBootTest
class WarehouseApplicationTests {

    @Autowired
    private HelloController helloController; // 测试对象
    @Autowired
    private BusMapper busMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Test // 每一个测试方法上都需要添加@Test注解
    public void testHello(){
        List<Bus> str1 = helloController.sayHelloBus();
        System.out.println(str1);
        List<Order> str2 = helloController.sayHelloOrder();
        System.out.println(str2);
    }
//    @Test
//    public void testClass() {
//        Class a = new Class();
//        a.setBasicPrice(100);
//        System.out.println(a.getBasicPrice());
//    }
//    @Test
//    public void testStation() {
//        List<Station> a=stationMapper.findALLStation("西安站");
//        System.out.println(a);
//    }

    @Test
    public void testBus() {
        List<Bus> a=busMapper.findBylicense("陕S0978");
        System.out.println(a);
    }
    @Test
    public void testOrder() {
        List<Order> c=orderMapper.findByorderId(1);
        System.out.println(c);
    }
}
