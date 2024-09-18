package com.space.spacesinspace.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.space.spacesinspace")
@MapperScan(basePackages = "com.space.spacesinspace", annotationClass = Mapper.class)
public class SpacesInSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpacesInSpaceApplication.class, args);
    }

}
