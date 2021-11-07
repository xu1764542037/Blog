package com.laoxu.blog.dao.inter;

import com.laoxu.blog.entity.AbsSuperObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IDraft extends IDoDate{

    List<Map<String ,Object>> selectAllDraft(Map<String, Object> cons);

}
