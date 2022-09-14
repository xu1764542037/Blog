package com.laoxu.blog.bll.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.dao.inter.IArticle;
import com.laoxu.blog.dao.inter.IDoDate;
import com.laoxu.blog.dao.inter.IType;
import com.laoxu.blog.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class TypeServiceImpl extends AbsSuperService implements com.laoxu.blog.bll.inter.IType {
    @Autowired
    private IType dao;

    @Autowired
    private Type type;

    @Autowired
    private Article article;

    @Autowired
    private Draft draft;

    @Override
    public IDoDate getDao() {
        return dao;
    }


    public BackReturn selectEverTypeNum(Map<String, Object> cons) {
        BackReturn back = new BackReturn();//检查参数
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空");
            back.setObj(null);
            return back;
        }
        //调用数据访问层查找功能

        Type type = new Type();
        int typeId_1 = 0;
        int typeId_2 = 0;
        int typeId_3 = 0;
        int typeId_4 = 0;
        int typeId_5 = 0;

        System.out.println("======================");
        System.out.println("======================");
        System.out.println("======================");


        for (int typeId = 1; typeId <6 ; typeId++) {
            Map<String, Object> map = new HashMap<>();
            map.put("type_id", typeId);
            List<Map<String ,Object>> selects = dao.selectEverTypeNum(map);
//            System.out.println(selects);
            if (typeId == 1) {
                typeId_1 = selects.size();
            } else if (typeId == 2 ) {
                typeId_2 = selects.size();
            } else if (typeId == 3 ) {
                typeId_3 = selects.size();
            } else if (typeId == 4 ) {
                typeId_4 = selects.size();
            } else  {
                typeId_5 = selects.size();
            }
        }

        HashMap<String, Object> typeNum = new HashMap<>();
        typeNum.put("typeId_1",typeId_1);
        typeNum.put("typeId_2",typeId_2);
        typeNum.put("typeId_3",typeId_3);
        typeNum.put("typeId_4",typeId_4);
        typeNum.put("typeId_5",typeId_5);


        if (typeNum!=null){
            back.setCode("200");
            back.setMessage("查找成功");

        }else {
            back.setCode("002");
            back.setMessage("查找失败");
        }
        back.setObj(typeNum);
        return back;
//        return null;
    }


    @Override
    public BackReturn setTypeByArticle(Article article, int type_id) {
        BackReturn back=new BackReturn();
        //判断参数的有效性
        if (article==null || type_id == 0) {
            back.setCode("0");
            back.setMessage("文章id和类型id不能为空！");
            back.setObj(null);
            return back;
        }
        int result = 0;
        type.setId(type_id);
        result= dao.setTypeByArticle(article,type);

        if (result > 0){
            back.setCode("1");
            back.setMessage("添加类型成功！");
        }else{
            back.setCode("-1");
            back.setMessage("添加类型失败！");
        }
        back.setObj(result);
        return back;
    }

    @Override
    public BackReturn setTypeByDraft(Draft draft, int type_id) {
        BackReturn back=new BackReturn();
        //判断参数的有效性
        if (draft==null || type_id==0) {
            back.setCode("0");
            back.setMessage("文章id和标签id不能为空！");
            back.setObj(null);
            return back;
        }
        int result = 0;
        type.setId( type_id);
        result= dao.setTypeByDraft(draft,type);

        if (result > 0){
            back.setCode("1");
            back.setMessage("添加标签成功！");
        }else{
            back.setCode("-1");
            back.setMessage("添加标签失败！");
        }
        back.setObj(result);
        return back;
    }

    @Override
    public BackReturn selectTypeByArticle(Map<String, Object> object) {
        BackReturn back = new BackReturn();//检查参数
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空");
            back.setObj(null);
            return back;
        }
        //调用数据访问层查找功能
        String article_id = (String) object.get("article_id");
        Map<String,Object> map = new HashMap<>();
        map.put("article_id",article_id);
        List<Object> list = dao.selectTypeByArticle(map);
        List<Integer> type_ids = new ArrayList<>();
        int typeName = 0;
        for (Object type : list) {
            Map<String,Object> types = (Map<String, Object>) type;
            int type_id = (Integer) types.get("type_id");
            typeName =  type_id;
        }

        if (list != null){
            back.setCode("200");
            back.setMessage("查找成功");

        }else {
            back.setCode("002");
            back.setMessage("查找失败");
        }
        back.setObj(typeName);
        return back;
    }
}
