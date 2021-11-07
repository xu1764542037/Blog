package com.laoxu.blog.bll.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.dao.inter.IArticle;
import com.laoxu.blog.dao.inter.IDoDate;
import com.laoxu.blog.dao.inter.IType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TypeServiceImpl extends AbsSuperService {
    @Autowired
    private IType dao;


    @Override
    public IDoDate getDao() {
        return dao;
    }
}
