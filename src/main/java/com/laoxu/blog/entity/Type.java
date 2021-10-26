package com.laoxu.blog.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Type extends AbsSuperObject{
    private int id;
    private String name;
}
