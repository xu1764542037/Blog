package com.laoxu.blog.bll.inter;

import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.entity.Draft;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ILabel {
    BackReturn setLabelByArticle(Article article, List label_ids);

    BackReturn setLabelByDraft(Draft draft, List label_ids);

    BackReturn selectLabelByArticle(Map<String,Object> object);

}
