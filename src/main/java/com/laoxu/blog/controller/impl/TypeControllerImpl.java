package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.TypeServiceImpl;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
@RequestMapping(value ="/type",method = RequestMethod.POST)
public class TypeControllerImpl extends AbsSuperController {
    @Autowired
    private TypeServiceImpl bll;

    @Autowired
    private Type type;

    @Override
    public AbsSuperService getService() {
        setModel(type);
        return bll;
    }
}
