package com.laoxu.blog.controller.inter;

import com.laoxu.blog.entity.BackReturn;

import java.util.Map;

public interface IType {
    BackReturn setTypeByArticle(Map<String,Object> object);

    BackReturn setTypeByDraft(Map<String,Object> object);


    BackReturn selectTypeByArticle(Map<String,Object> object);
}
