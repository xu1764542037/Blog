package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.impl.MailServiceImpl;
import com.laoxu.blog.dao.inter.ICode;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.entity.Code;
import com.laoxu.blog.entity.UsersBo;
import com.laoxu.blog.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

//强制卸载命令：rm -rf /www/server/mysql
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/mail")
public class MailControllerImpl {
    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private ICode iCode;

    @Autowired
    private Code code;

    @RequestMapping(value = "/getCheckCode", method = RequestMethod.POST)
    public RestResponse<String> getCheckCode(@RequestBody UsersBo usersBo){
        log.info("进入方法getCheckCode:"+usersBo.toString());
        RestResponse restResponse = new RestResponse();
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);

        String message = "您的注册验证码为："+checkCode+",验证码时效大约只有10分钟！";
        code.setCode(checkCode);
        iCode.add(code);


        try {
            mailService.sendSimpleMail(usersBo.getEmail(), "注册验证码", message);
        }catch (Exception e){
            restResponse.setData(e);
            return restResponse;
        }
        restResponse.setData(checkCode);
        return restResponse;
    }

    @CrossOrigin
    @RequestMapping(value = "/CheckCode", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn CheckCode(@RequestBody Map<String,Object> object){
        BackReturn back = new BackReturn();
        try {
            return mailService.CheckCode(object);
        } catch (Exception e) {
            back.setCode("202");
            back.setMessage("修改失败");
            back.setObj(null);
        }

        return back;

    }
}

