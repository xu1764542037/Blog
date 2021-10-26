package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.ArticleService;
import com.laoxu.blog.bll.impl.LabelService;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
@RequestMapping(value ="/label",method = RequestMethod.POST)
public class LabelController extends AbsSuperController {
    @Autowired
    private LabelService bll;

    @Autowired
    private Label label;

    @Override
    public AbsSuperService getService() {
        setModel(label);
        return bll;
    }
}
