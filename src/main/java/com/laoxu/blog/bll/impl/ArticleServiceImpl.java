package com.laoxu.blog.bll.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.dao.inter.ILabel;
import com.laoxu.blog.dao.inter.IArticle;
import com.laoxu.blog.dao.inter.IDoDate;
import com.laoxu.blog.dao.inter.IType;
import com.laoxu.blog.entity.AbsSuperObject;
import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ArticleServiceImpl extends AbsSuperService {
    @Autowired
    private IArticle dao;

    @Autowired
    private IType iType;

    @Autowired
    private ILabel iLabel;

    @Autowired
    private Article article;

    @Override
    public IDoDate getDao() {
        return dao;
    }


    @Override
    public BackReturn select(Map<String, Object> cons) {

        BackReturn back = new BackReturn();//检查参数
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空");
            back.setObj(null);
            return back;
        }
        //调用数据访问层查找功能


        Map<String ,Object> select = getDao().select(cons);
        System.out.println("======================");
        System.out.println("======================");

        System.out.println(select);

        System.out.println("======================");

        if (select == null) {
            back.setCode("001");
            back.setMessage("数据访问层对象为空");
            back.setObj(null);
            return back;
        }

        Map<String,Object> typeMap = new HashMap<>();
        typeMap.put("id",select.get("type"));
        Map<String, Object> typeResult = iType.select(typeMap);
        Object name = typeResult.get("name");
        select.put("typeName",name);

        Map<String,Object> LabelMap = new HashMap<>();
        String id = (String) select.get("id");
        LabelMap.put("article_id",id);
        List<Object> labelResult = iLabel.selectLabelByArticle(LabelMap);
        List<Integer> label_ids = new ArrayList<>();
        for (Object label : labelResult) {
            Map<String,Object> labels = (Map<String, Object>) label;
            int label_id = (Integer) labels.get("label_id");
            label_ids.add(label_id);
        }
        List<Object> labelNames = iLabel.batchSelectLabel(label_ids);
        select.put("labelNames",labelNames);


        if (select!=null){
            back.setCode("200");
            back.setMessage("查找成功");
        }else {
            back.setCode("002");
            back.setMessage("查找失败");
        }
        back.setObj(select);
        return back;
//        return null;
    }

    /**
     * 查找
     */
    public BackReturn selectAllArticle(Map<String, Object> cons) {

        BackReturn back = new BackReturn();//检查参数
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空");
            back.setObj(null);
            return back;
        }
//        System.out.println("======================");
//        System.out.println("======================");
//        System.out.println(cons);
        //调用数据访问层查找功能
        List<Map<String ,Object>> selects = dao.selectAllArticle(cons);
//        System.out.println("======================");
//        System.out.println("======================");
//
//        System.out.println(selects);

//        System.out.println("======================");
//        System.out.println("======================");

        for (Map<String, Object> select : selects) {
            Map<String,Object> typeMap = new HashMap<>();
            typeMap.put("id",select.get("type"));
            Map<String, Object> typeResult = iType.select(typeMap);
            Object name = typeResult.get("name");
            select.put("typeName",name);

            Map<String,Object> LabelMap = new HashMap<>();
            String id = (String) select.get("id");
            LabelMap.put("article_id",id);
            List<Object> labelResult = iLabel.selectLabelByArticle(LabelMap);
            List<Integer> label_ids = new ArrayList<>();
            for (Object label : labelResult) {
                Map<String,Object> labels = (Map<String, Object>) label;
                int label_id = (Integer) labels.get("label_id");
                label_ids.add(label_id);
            }
            List<Object> labelNames = iLabel.batchSelectLabel(label_ids);
            select.put("labelNames",labelNames);
        }


        if (selects!=null){
            back.setCode("200");
            back.setMessage("查找成功");

        }else {
            back.setCode("002");
            back.setMessage("查找失败");
        }
        back.setObj(selects);
        return back;
//        return null;
    }

    @Override
    public BackReturn findByPage(Map<String, Object> cons, int pageIndex, int rowAccount) {
        BackReturn back=new BackReturn();
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空！");
            back.setObj(null);
            return back;
        }
        //查询符合条件的记录总行数
        int rowsTotal=getDao().rowsCount(cons);
        if (rowsTotal<=0){
            back.setCode("0");
            back.setMessage("系统没有查询到符合条件的记录！");
            back.setObj(null);
            return back;
        }
        cons.put("start",pageIndex*rowAccount);
        cons.put("rowAccount",rowAccount);
        //调用数据访问层查找功能
        List<Map<String,Object>> result=getDao().findByPage(cons);


        for (Map<String, Object> select : result) {
            Map<String,Object> typeMap = new HashMap<>();
            typeMap.put("id",select.get("type"));
            Map<String, Object> typeResult = iType.select(typeMap);
            Object name = typeResult.get("name");
            select.put("typeName",name);

            Map<String,Object> LabelMap = new HashMap<>();
            String id = (String) select.get("id");
            LabelMap.put("article_id",id);
            List<Object> labelResult = iLabel.selectLabelByArticle(LabelMap);
            List<Integer> label_ids = new ArrayList<>();
            for (Object label : labelResult) {
                Map<String,Object> labels = (Map<String, Object>) label;
                int label_id = (Integer) labels.get("label_id");
                label_ids.add(label_id);
            }
            List<Object> labelNames = iLabel.batchSelectLabel(label_ids);
            select.put("labelNames",labelNames);
        }

        if (result!=null && result.size()>0){
            back.setCode(String.valueOf(rowsTotal));
            back.setMessage("已经查到符合您条件的数据！");
        }else{
            back.setCode("0");
            back.setMessage("系统没有查询到符合您要求的数据！");
        }
        back.setObj(result);
        return back;
    }


    @Override
    public BackReturn update(AbsSuperObject obj) {
        System.out.println("===========================");
        System.out.println(obj);
        System.out.println("===========================");

        return super.update(obj);
    }

}
