package com.laoxu.blog.controller.inter;

import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;

import java.util.ArrayList;
import java.util.Map;

public interface ILabel {
    BackReturn setLabelByArticle(Map<String,Object> object);

    BackReturn setLabelByDraft(Map<String,Object> object);


    BackReturn selectLabelByArticle(Map<String,Object> object);



}
