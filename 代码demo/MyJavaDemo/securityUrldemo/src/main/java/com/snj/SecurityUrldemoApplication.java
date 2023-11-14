package com.snj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.snj.mapper")
public class SecurityUrldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityUrldemoApplication.class, args);
    }

}
