package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.ArticleService;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
@RequestMapping(value ="/article",method = RequestMethod.POST)
public class ArticleController extends AbsSuperController {
    @Autowired
    private ArticleService bll;

    @Autowired
    private Article article;

    @Override
    public AbsSuperService getService() {
        setModel(article);
        return bll;
    }
}
