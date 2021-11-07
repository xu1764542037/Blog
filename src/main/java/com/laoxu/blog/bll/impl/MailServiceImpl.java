package com.laoxu.blog.bll.impl;

import com.laoxu.blog.dao.inter.ICode;
import com.laoxu.blog.entity.BackReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@Transactional
public class MailServiceImpl {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ICode iCode;

    public void sendSimpleMail(String to,String title,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(content);
        javaMailSender.send(mailMessage);
        log.info("邮件发送成功");
    }

    public BackReturn CheckCode(Map<String,Object> cons){
        BackReturn back = new BackReturn();//检查参数
        //调用数据访问层查找功能
        Map<String ,Object> select = iCode.select(cons);
        if (select!=null){
            back.setCode("200");
            back.setMessage("查找成功");

        }else {
            back.setCode("002");
            back.setMessage("查找失败");
        }

        back.setObj(select);
        return back;
    }
}

