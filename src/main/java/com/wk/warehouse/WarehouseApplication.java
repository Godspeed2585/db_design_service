package com.wk.warehouse;

import jdk.jfr.Enabled;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
// mapper扫描会给所有该包下的mapper接口生成一个类型首字母小写的对象
@MapperScan("com.wk.warehouse.mapper")

public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
