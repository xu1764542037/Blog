package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.ArticleServiceImpl;
import com.laoxu.blog.bll.impl.LabelServiceImpl;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.dao.inter.ILabel;
import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value ="/article",method = RequestMethod.POST)
public class ArticleControllerImpl extends AbsSuperController {
    @Autowired
    private ArticleServiceImpl bll;

    @Autowired
    private Article article;

    @Autowired
    private LabelServiceImpl labelService;

    @Autowired
    private ILabel iLabel;

    @Override
    public AbsSuperService getService() {
        setModel(article);
        return bll;
    }



    @Override
    @CrossOrigin
    @RequestMapping(value = "/addArticle",method = RequestMethod.POST)
    @ResponseBody
    public BackReturn add(@RequestBody Map<String, Object> object) {
        String id = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());

        article.setId(id);
        article.setName(object.get("name").toString());
        article.setSummary(object.get("summary").toString());
        article.setText(object.get("text").toString());
        article.setType(object.get("type").toString());
        article.setState(object.get("state").toString());

        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        String StartTime = ""+year+"-"+month+"-"+day+"";
        article.setStartTime(StartTime);
        article.setEndTime(StartTime);


        BackReturn back = bll.add(article);

        article.setId(id);
        List label_ids = (List) object.get("labels");
        labelService.setLabelByArticle(article,label_ids);

        return back;
    }

    @CrossOrigin
    @RequestMapping(value = "/selectAllArticle", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn selectAllArticle(@RequestBody Map<String,Object> object) {
        BackReturn back = bll.selectAllArticle(object);
        if (back.getObj()!=null && back.getObj() instanceof List){
            List result = (List) back.getObj();
            if (result.size()>0){
                back.setObj(result);
            }else {
                back.setObj(null);
            }
            return back;
        }else {
            return back;
        }
    }

    @Override
    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn update(@RequestBody Map<String, Object> object) {
        BackReturn back = new BackReturn();//检查参数
        if (object == null) {
            back.setCode("200");
            back.setMessage("参数为空，请重新传值");
            return back;
        }

        System.out.println(object);


        article.setId(object.get("id").toString());
        article.setName(object.get("name").toString());
        article.setSummary(object.get("summary").toString());
        article.setText(object.get("text").toString());
        article.setType(object.get("type").toString());

        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        String StartTime = ""+year+"-"+month+"-"+day+"";
        article.setEndTime(StartTime);

        iLabel.deleteByArticle(article);
        List label_ids = (List) object.get("labels");
        labelService.setLabelByArticle(article,label_ids);

        return bll.update(article);
    }
}
