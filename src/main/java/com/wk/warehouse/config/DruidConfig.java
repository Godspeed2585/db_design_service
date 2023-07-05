package com.wk.warehouse.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

    @Bean
    // 将配置文件中以spring.datasource开头的属性注入到DruidDataSource对象的属性中
    @ConfigurationProperties(prefix="spring.datasource")
    public DruidDataSource getDruid(){
        return new DruidDataSource();
    }
}
