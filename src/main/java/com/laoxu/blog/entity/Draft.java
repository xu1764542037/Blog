package com.laoxu.blog.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Draft extends AbsSuperObject{
    //草稿类
    private String id;
    private String name;
    private String summary;
    private String text;
    private String type;
    private String state;
    private String StartTime;
    private String EndTime;
}
