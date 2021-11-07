package com.laoxu.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class AbsSuperObject implements Serializable {
    private String name;
    private Date Createdate = new Date();
    private String descripition;
    private String status;

}
