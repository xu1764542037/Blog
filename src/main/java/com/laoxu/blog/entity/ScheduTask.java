package com.laoxu.blog.entity;

import com.laoxu.blog.dao.inter.ICode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ScheduTask {
    private Logger logger =Logger.getLogger(ScheduTask.class);


    @Autowired
    private ICode iCode;

    @Autowired
    private Code code;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void testSca(){
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("测试1："+name);
        logger.info("定时任务1:删除code表数据"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
        iCode.delete(code);
    }
}
