package com.example.ojbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ojbackend.mapper")
public class OjbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjbackendApplication.class, args);
    }

}
