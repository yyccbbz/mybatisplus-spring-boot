package com.baomidou.springboot.config;

import com.baomidou.springboot.excel.ExcelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: CuiCan
 * @Date: 2017-6-11
 * @Time: 12:36
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.baomidou.springboot.config")
// 配置扫描包
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class SpringConfig {

    @Bean
    public ExcelContext excelContext() {
        return new ExcelContext("excel-config.xml");
    }

}


