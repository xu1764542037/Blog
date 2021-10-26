package com.laoxu.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.laoxu.blog.bll","com.laoxu.blog.inspect",
        "com.laoxu.blog.entity","com.laoxu.blog.dao",
        "com.laoxu.blog.controller","com.laoxu.blog.utils"})
@MapperScan(basePackages = {"com.laoxu.blog.dao.inter"})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
//        System.out.println("老徐最棒了");
    }

}
