package com.ly.personalsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ly.personalsys.mapper")
@SpringBootApplication

public class PersonalSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalSysApplication.class, args);
    }

}
