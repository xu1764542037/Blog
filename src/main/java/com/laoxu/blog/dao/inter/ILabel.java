package com.laoxu.blog.dao.inter;

import com.laoxu.blog.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ILabel extends IDoDate {
    List<Object> batchSelectLabel(List<Integer> ids);

    int setLabelByArticle(Article article, Label label);

    int deleteByArticle(Article article);


    int setLabelByDraft(Draft draft, Label label);

    List<Object> selectLabelByArticle(Map<String,Object> map);

    List<Object> selectLabelByDraft(Map<String,Object> map);


}
