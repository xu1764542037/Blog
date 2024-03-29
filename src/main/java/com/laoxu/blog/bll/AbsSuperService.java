package com.laoxu.blog.bll;

import com.laoxu.blog.dao.inter.IDoDate;
import com.laoxu.blog.entity.AbsSuperObject;
import com.laoxu.blog.entity.BackReturn;

import java.util.List;
import java.util.Map;

public abstract class AbsSuperService {

    //定义数据访问层抽象方法
    public abstract IDoDate getDao();

    /**
     * 条件检测方法
     */
    private BackReturn checkCondition(AbsSuperObject obj){
        //定义结果
        BackReturn back = new BackReturn();
        if (obj==null || getDao()==null){
            back.setCode("000");
            back.setMessage("系统检测到数据访问层对象为空或者操作参数为空！");
            back.setObj(false);
        }else {
            back.setCode("100");
            back.setMessage("系统参数检查通过！");
            back.setObj(true);
        }
        return back;
    }

    /**
     * 新增
     */
    public BackReturn add(AbsSuperObject obj){
        BackReturn back = checkCondition(obj);//检查参数
        if ((Boolean) back.getObj()==false) {
            return back;
        }
        //调用数据访问层新增功能
        int result = getDao().add(obj);
        if (result>0){
            back.setCode("200");
            back.setMessage("新增成功");
        }else {
            back.setCode("002");
            back.setMessage("新增失败");
        }
        back.setObj(result);
        return back;
    }

    /**
     * 修改
     * @param obj
     * @return
     */
    public BackReturn update(AbsSuperObject obj){
        BackReturn back = checkCondition(obj);//检查参数
        if ((Boolean) back.getObj()==false) {
            return back;
        }
        //调用数据访问层新增功能
        int result = getDao().update(obj);
        if (result>0){
            back.setCode("200");
            back.setMessage("修改成功");
        }else {
            back.setCode("002");
            back.setMessage("修改失败");
        }
        back.setObj(result);
        return back;
    }

    /**
     * 删除
     * @param obj
     * @return
     */
    public BackReturn delete(AbsSuperObject obj){
        BackReturn back = checkCondition(obj);//检查参数
        if ((Boolean) back.getObj()==false) {
            return back;
        }
        //调用数据访问层新增功能
        int result = getDao().delete(obj);
        if (result>0){
            back.setCode("200");
            back.setMessage("删除成功");
        }else {
            back.setCode("002");
            back.setMessage("删除失败");
        }
        back.setObj(result);
        return back;
    }

    /**
     * 查找
     */
    public BackReturn select(Map<String,Object> cons){
        BackReturn back = new BackReturn();//检查参数
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空");
            back.setObj(null);
            return back;
        }
        //调用数据访问层查找功能
        Map<String ,Object> select = getDao().select(cons);
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



    /**
     * 查找需要的行数
     * @param cons
     * @return
     */
    public BackReturn distinctSelect(Map<String,Object> cons){
        BackReturn back=new BackReturn();
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空！");
            back.setObj(null);
            return back;
        }
        //调用数据访问层查找功能
        List<AbsSuperObject> result=getDao().distinctSelect(cons);
        if (result!=null && result.size()>0){
            back.setCode("200");
            back.setMessage("已经查到符合您条件的数据！");
        }else{
            back.setCode("002");
            back.setMessage("系统没有查询到符合您要求的数据！");
        }
        back.setObj(result);
        return back;
    }

    /*分页查找*/
    public BackReturn findByPage(Map<String,Object> cons,int pageIndex,int rowAccount){
        BackReturn back=new BackReturn();
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空！");
            back.setObj(null);
            return back;
        }

        //查询符合条件的记录总行数
        int rowsTotal=getDao().rowsCount(cons);
        if (rowsTotal<=0){
            back.setCode("0");
            back.setMessage("系统没有查询到符合条件的记录！");
            back.setObj(null);
            return back;
        }
        cons.put("start",pageIndex*rowAccount);
        cons.put("rowAccount",rowAccount);
        //调用数据访问层查找功能
        List<Map<String,Object>> result=getDao().findByPage(cons);



        if (result!=null && result.size()>0){
            back.setCode(String.valueOf(rowsTotal));
            back.setMessage("已经查到符合您条件的数据！");
        }else{
            back.setCode("0");
            back.setMessage("系统没有查询到符合您要求的数据！");
        }
        back.setObj(result);
        return back;
    }


    /**
     * 批量查找
     */
    public BackReturn batchSelect(Map<String,Object> cons){
        BackReturn back = new BackReturn();//检查参数
        if (getDao()==null) {
            back.setCode("000");
            back.setMessage("数据访问层对象为空");
            back.setObj(null);
            return back;
        }
        //调用数据访问层查找功能
        List<AbsSuperObject> select = getDao().batchSelect(cons);
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
