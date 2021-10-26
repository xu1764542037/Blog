package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.impl.MailService;
import com.laoxu.blog.entity.UsersBo;
import com.laoxu.blog.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

//强制卸载命令：rm -rf /www/server/mysql
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/getCheckCode", method = RequestMethod.POST)
    public RestResponse<String> getCheckCode(@RequestBody UsersBo usersBo){
        log.info("进入方法getCheckCode:"+usersBo.toString());
        RestResponse restResponse = new RestResponse();
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(usersBo.getEmail(), "注册验证码", message);
        }catch (Exception e){
            restResponse.setData(e);
            return restResponse;
        }
        restResponse.setData(checkCode);
        return restResponse;
    }
}

