package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.TypeServiceImpl;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.controller.inter.IType;
import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.entity.Draft;
import com.laoxu.blog.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value ="/type",method = RequestMethod.POST)
public class TypeControllerImpl extends AbsSuperController implements IType {
    @Autowired
    private TypeServiceImpl bll;

    @Autowired
    private Article article;

    @Autowired
    private Draft draft;

    @Autowired
    private Type type;

    @Override
    public AbsSuperService getService() {
        setModel(type);
        return bll;
    }

    @Override
    @RequestMapping(value = "setTypeByArticle", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn setTypeByArticle(@RequestBody Map<String, Object> object) {
        BackReturn back = new BackReturn();
        if (object == null) {
            back.setCode("200");
            back.setMessage("参数为空，请重新传值");
            return back;
        }
        article.setId((String) object.get("article_id"));
        int type_id = (Integer) object.get("type");
        return bll.setTypeByArticle(article,type_id);
    }

    @Override
    @RequestMapping(value = "setTypeByDraft", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn setTypeByDraft(@RequestBody Map<String, Object> object) {
        BackReturn back = new BackReturn();
        if (object == null) {
            back.setCode("200");
            back.setMessage("参数为空，请重新传值");
            return back;
        }
        draft.setId((String) object.get("article_id"));
        int type_id = (Integer) object.get("type");
        return bll.setTypeByDraft(draft,type_id);
    }


    @Override
    @RequestMapping(value = "selectTypeByArticle", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn selectTypeByArticle(Map<String,Object> object) {
        BackReturn back = new BackReturn();
        if (object == null) {
            back.setCode("200");
            back.setMessage("参数为空，请重新传值");
            return back;
        }

        return bll.selectTypeByArticle(object);
    }

    @CrossOrigin
    @RequestMapping(value = "/selectEverTypeNum", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn selectEverTypeNum(@RequestBody Map<String,Object> object) {
        BackReturn back = bll.selectEverTypeNum(object);
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
}
