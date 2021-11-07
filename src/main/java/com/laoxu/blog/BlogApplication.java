package com.laoxu.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//定时任务
@MapperScan("com.laoxu.blog.dao.inter")
@ComponentScan(basePackages = {"com.laoxu.blog.bll", "com.laoxu.blog.inspect", "com.laoxu.blog.entity", "com.laoxu.blog.dao.inter", "com.laoxu.blog.controller", "com.laoxu.blog.utils"})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
//        System.out.println("老徐最棒了");
    }

}
