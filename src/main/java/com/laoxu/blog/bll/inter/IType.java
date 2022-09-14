package com.laoxu.blog.bll.inter;

import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.entity.Draft;

import java.util.List;
import java.util.Map;

public interface IType {
    BackReturn setTypeByArticle(Article article, int type_id);

    BackReturn setTypeByDraft(Draft draft, int type_id);

    BackReturn selectTypeByArticle(Map<String,Object> object);
}
