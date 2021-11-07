package com.laoxu.blog.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Article extends AbsSuperObject{
    private String id;
    private String name;
    private String summary;
    private String text;
    private String type;
    private String state;
    private String StartTime;
    private String EndTime;
}
