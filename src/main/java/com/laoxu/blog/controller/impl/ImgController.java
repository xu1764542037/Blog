package com.laoxu.blog.controller.impl;

import com.alibaba.fastjson.JSONObject;

import com.laoxu.blog.utils.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class ImgController {
    /**
     * 图片上传
     */
    @PostMapping("/uploadFile")
    public String uploadimg(@RequestParam("image") MultipartFile mFile) throws IOException {
        // 定义存储图片的地址
//        String folder = "D:/note/assets"; //windows
        String folder = "/home/note/assets";
        // 文件夹
        File imgFolder = new File(folder);
        // 获取文件名
        String fname = mFile.getOriginalFilename();
        // 获取文件后缀
        String ext = "." + fname.substring(fname.lastIndexOf(".")+1);
        // 获取时间字符串
        String dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        // 生成新的文件名
        String newFileName = dateTimeFormatter + UUID.randomUUID().toString().replaceAll("-","") + ext;

//        "http://59.52.20.100:8088/mdernsky/note/assets/"

//        String backName = "http://localhost:8188/blog/note/assets/" + newFileName;
                String backName = "http://106.15.38.204:8188/blog/note/assets/" + newFileName;

        // 文件在本机的全路径
        File filePath = new File(imgFolder, newFileName);
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try{
            mFile.transferTo(filePath);
            // 返回文件名
//            return filePath.getName();
            return backName;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    //上传md文件
    @CrossOrigin
    @RequestMapping(value = "/saveMd",method = RequestMethod.POST)
    @ResponseBody
    //注意参数传递是以json格式，因此用@RequestBody定义接收参数
    public String saveMd(@RequestBody JSONObject param){
        //取出java中对应参数的值

        String str = param.getString("content");
        String name=param.getString("name");
        String typeId=param.getString("typeId");
//        文件保存路径  F:\note\555.md
        String filepath="/home/note/"+name+".md";
//                String filepath="D:/note/"+name+".md";

        FileUtil.string2File(str,filepath);
        return "ok";
    }

}



