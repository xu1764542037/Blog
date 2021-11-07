package com.laoxu.blog.inspect;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/note/assets/**").addResourceLocations("file:" + "D:/note/assets/");
//        registry.addResourceHandler("/note/assets/**").addResourceLocations("file:" + "/home/note/assets/");
        registry.addResourceHandler("/img/background/**").addResourceLocations("file:" + "/home/img/bg/");


    }
}




