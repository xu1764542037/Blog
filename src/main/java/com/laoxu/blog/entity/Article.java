package com.laoxu.blog.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Article extends AbsSuperObject{
    private int id;
    private String name;
    private String summary;
    private String label;
    private String type;
    private String state;
    private String StartTime;
    private String EndTime;
    private String FileName;
}
