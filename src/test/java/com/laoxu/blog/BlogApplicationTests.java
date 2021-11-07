package com.laoxu.blog;

import com.laoxu.blog.bll.impl.LabelServiceImpl;
import com.laoxu.blog.dao.inter.IArticle;
import com.laoxu.blog.dao.inter.ILabel;
import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.entity.Label;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private ILabel iLabel;

    @Autowired
    private Label label;

    @Autowired
    private  IArticle iArticle;

    @Autowired
    private Article article;

    @Autowired
    private LabelServiceImpl labelService;



    @Test
    void contextLoads() {
//        Calendar calendar=Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH)+1;
//        int day = calendar.get(Calendar.DATE);
//        int hour = calendar.get(Calendar.HOUR);
//        int min = calendar.get(Calendar.MINUTE);
//        int s = calendar.get(Calendar.SECOND);

        String dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        System.out.println(Integer.parseInt(dateTimeFormatter));

        //获取现在的时间的秒数
//        Date date= new Date(System.currentTimeMillis());
//
//        System.out.println(date.getTime());

    }

    @Test
    void selectLabel() {
        Map<String,Object> map = new HashMap<>();
        System.out.println(iLabel.select(map));
//        System.out.println(iArticle.select(map));
    }

    @Test
    void selectLabelByArticle() {
        Map<String,Object> map = new HashMap<>();
        map.put("article_id","20211028100235");
        BackReturn backReturn = labelService.selectLabelByArticle(map);
        System.out.println(backReturn);
    }

    @Test
    void os() {
        String os = System.getProperty("os.name");
        System.out.println(os);

            String folder;
        if (System.getProperty("os.name").toLowerCase().contains("windows")){
            folder = "D:/note/assets"; //windows

        } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {

            folder = "/home/note/assets"; //Linux
        }
    }


    @Test
    void deleteByArticle() {
        article.setId("20211103142554");
        iLabel.deleteByArticle(article);

    }


}
