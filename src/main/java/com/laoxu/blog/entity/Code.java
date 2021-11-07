package com.laoxu.blog.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Code extends AbsSuperObject{
    private int id;
    private String code;
}
