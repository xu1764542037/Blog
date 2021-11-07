package com.laoxu.blog.dao.inter;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IArticle extends IDoDate{

    List<Map<String ,Object>> selectAllArticle(Map<String, Object> cons);

}
