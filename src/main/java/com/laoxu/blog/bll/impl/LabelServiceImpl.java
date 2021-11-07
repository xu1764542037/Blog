package com.laoxu.blog.bll.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.dao.inter.IDoDate;
import com.laoxu.blog.dao.inter.ILabel;
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
public class LabelServiceImpl extends AbsSuperService implements com.laoxu.blog.bll.inter.ILabel {
    @Autowired
    private ILabel dao;

    @Autowired
    private Label label;

    @Autowired
    private Article article;

    @Autowired
    private Draft draft;

    @Override
    public IDoDate getDao() {
        return dao;
    }


    @Override
    public BackReturn setLabelByArticle(Article article, List label_ids) {
        BackReturn back=new BackReturn();
        //判断参数的有效性
        if (article==null || label_ids==null) {
            back.setCode("0");
            back.setMessage("文章id和标签id不能为空！");
            back.setObj(null);
            return back;
        }
        int result = 0;
        for (Object label_id : label_ids) {
            label.setId((Integer) label_id);
            result= dao.setLabelByArticle(article,label);
        }
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
    public BackReturn setLabelByDraft(Draft draft, List label_ids) {
        BackReturn back=new BackReturn();
        //判断参数的有效性
        if (draft==null || label_ids==null) {
            back.setCode("0");
            back.setMessage("文章id和标签id不能为空！");
            back.setObj(null);
            return back;
        }
        int result = 0;
        for (Object label_id : label_ids) {
            label.setId((Integer) label_id);
            result= dao.setLabelByDraft(draft,label);
        }
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
    public BackReturn selectLabelByArticle(Map<String, Object> object) {
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
        List<Object> list = dao.selectLabelByArticle(map);
        List<Integer> label_ids = new ArrayList<>();
        for (Object label : list) {
            Map<String,Object> labels = (Map<String, Object>) label;
            int label_id = (Integer) labels.get("label_id");
            label_ids.add(label_id);
        }
        List<Object> labelNames = dao.batchSelectLabel(label_ids);


        if (list != null){
            back.setCode("200");
            back.setMessage("查找成功");

        }else {
            back.setCode("002");
            back.setMessage("查找失败");
        }
        back.setObj(labelNames);
        return back;
    }
}


