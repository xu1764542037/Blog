package com.laoxu.blog.dao.inter;

import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.Draft;
import com.laoxu.blog.entity.Label;
import com.laoxu.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IType extends IDoDate {
    String batchSelectType(int id);

    List<Map<String ,Object>> selectEverTypeNum(Map<String, Object> cons);


    int setTypeByArticle(Article article, Type type);

    int deleteByArticle(Article article);


    int setTypeByDraft(Draft draft, Type type);

    List<Object> selectTypeByArticle(Map<String,Object> map);

    List<Object> selectTypeByDraft(Map<String,Object> map);
}
