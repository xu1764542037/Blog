package com.laoxu.blog.controller;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.entity.AbsSuperObject;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.utils.JSONAndObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public abstract class AbsSuperController {
    private AbsSuperObject model;

    public AbsSuperObject getModel() {
        return model;
    }

    public void setModel(AbsSuperObject model) {
        this.model = model;
    }

    //获取业务层对象
    public abstract AbsSuperService getService();

    /*获取客户端参数初始化*/
    public AbsSuperObject initParameter(Map<String,Object> p)
    {
        AbsSuperObject model= JSONAndObject.MapToJavaBean(p,getModel().getClass()); //获取页面对象
//        if (model.getId().trim().equals(""))
//            model.setId(Tool.CreateID());
        setModel(model);//设置操作实体对象
        return model;
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn add(@RequestBody Map<String,Object> object) {
        BackReturn back = new BackReturn();
        try {
            return getService().add(initParameter(object));
        } catch (Exception e) {
            back.setCode("202");
            back.setMessage("添加失败");
            back.setObj(null);
        }
        return back;
    }

    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn update(@RequestBody Map<String,Object> object) {
        BackReturn back = new BackReturn();
        try {
            return getService().update(initParameter(object));
        } catch (Exception e) {
            back.setCode("202");
            back.setMessage("修改失败");
            back.setObj(null);
        }
        return back;
    }

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn delete(@RequestBody Map<String,Object> object) {
        BackReturn back = new BackReturn();
        try {
            return getService().delete(initParameter(object));
        } catch (Exception e) {
            back.setCode("202");
            back.setMessage("删除失败");
            back.setObj(null);
        }
        return back;
    }


    @CrossOrigin
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn select(@RequestBody Map<String,Object> object) {
        BackReturn back = getService().select(object);
        if (back.getObj()!=null && back.getObj() instanceof List){
            List result = (List) back.getObj();
            if (result.size()>0){
                back.setObj(result);
            }else {
                back.setObj(null);
            }
            return back;
        }else {
            return back;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/batchSelect", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn batchSelect(@RequestBody Map<String,Object> object) {
        BackReturn back = getService().batchSelect(object);
        if (back.getObj()!=null && back.getObj() instanceof List){
            List result = (List) back.getObj();
            if (result.size()>0){
                back.setObj(result);
            }else {
                back.setObj(null);
            }
            return back;
        }else {
            return back;
        }
    }




    @CrossOrigin
    @RequestMapping(value = "distinctSelect",method = RequestMethod.POST)
    @ResponseBody
    public BackReturn distinctSelect(@RequestBody Map<String,Object> params){
        BackReturn back=new BackReturn();
        try{
            return getService().distinctSelect(params);
        }catch (Exception ex){
            back.setCode("-1");
            back.setMessage("系统异常："+ex.getMessage());
            back.setObj(null);
            return back;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "findByPage",method = RequestMethod.POST)
    @ResponseBody
    public BackReturn findByPage(@RequestBody Map<String,Object> params){
        BackReturn back=new BackReturn();
        //获取页码
        int pageIndex=params.get("page")==null?0:Integer.parseInt(params.get("page").toString());
        //获取每页显示的行数
        int rowsCount=params.get("rowAccount")==null?2:Integer.parseInt(params.get("rowAccount").toString());

        try{
            return getService().findByPage(params,pageIndex,rowsCount);
        }catch (Exception ex){
            back.setCode("-1");
            back.setMessage("系统异常："+ex.getMessage());
            back.setObj(null);
            return back;
        }
    }


}
