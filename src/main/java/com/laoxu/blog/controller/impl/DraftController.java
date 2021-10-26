package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.DraftService;
import com.laoxu.blog.bll.impl.TypeService;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.entity.Draft;
import com.laoxu.blog.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
@RequestMapping(value ="/draft",method = RequestMethod.POST)
public class DraftController extends AbsSuperController {
    @Autowired
    private DraftService bll;

    @Autowired
    private Draft draft;

    @Override
    public AbsSuperService getService() {
        setModel(draft);
        return bll;
    }
}
