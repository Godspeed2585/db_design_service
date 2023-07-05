package com.wk.warehouse;

import com.wk.warehouse.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.wk.warehouse.mapper.StationMapper;
import com.wk.warehouse.entity.Station;

import com.wk.warehouse.entity.Class;
import java.util.List;

@SpringBootTest
class WarehouseApplicationTests {

    @Autowired
    private HelloController helloController; // 测试对象
    @Autowired
    private StationMapper stationMapper;

    @Test // 每一个测试方法上都需要添加@Test注解
    public void testHello(){
        Station str = helloController.sayHello();
        System.out.println(str);
    }
    @Test
    public void testClass() {
        Class a = new Class();
        a.setBasicPrice(100);
        System.out.println(a.getBasicPrice());
    }
    @Test
    public void testStation() {
        List<Station> a=stationMapper.findALLStation("西安站");
        System.out.println(a);
    }
}
