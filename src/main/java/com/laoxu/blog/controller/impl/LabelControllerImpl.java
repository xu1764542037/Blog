package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.LabelServiceImpl;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.controller.inter.ILabel;
import com.laoxu.blog.entity.Article;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.entity.Draft;
import com.laoxu.blog.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value ="/label",method = RequestMethod.POST)
public class LabelControllerImpl extends AbsSuperController implements ILabel {
    @Autowired
    private LabelServiceImpl bll;

    @Autowired
    private Label label;

    @Autowired
    private Article article;

    @Autowired
    private Draft draft;

    @Override
    public AbsSuperService getService() {
        setModel(label);
        return bll;
    }

    @Override
    @RequestMapping(value = "setLabelByArticle", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn setLabelByArticle(@RequestBody Map<String, Object> object) {
        BackReturn back = new BackReturn();
        if (object == null) {
            back.setCode("200");
            back.setMessage("参数为空，请重新传值");
            return back;
        }
        article.setId((String) object.get("article_id"));
        List label_ids = (List) object.get("label_ids");
        return bll.setLabelByArticle(article,label_ids);
    }

    @Override
    @RequestMapping(value = "setLabelByDraft", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn setLabelByDraft(@RequestBody Map<String, Object> object) {
        BackReturn back = new BackReturn();
        if (object == null) {
            back.setCode("200");
            back.setMessage("参数为空，请重新传值");
            return back;
        }
        draft.setId((String) object.get("article_id"));
        List label_ids = (List) object.get("label_ids");
        return bll.setLabelByDraft(draft,label_ids);
    }


    @Override
    @RequestMapping(value = "selectLabelByArticle", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn selectLabelByArticle(Map<String,Object> object) {
        BackReturn back = new BackReturn();
        if (object == null) {
            back.setCode("200");
            back.setMessage("参数为空，请重新传值");
            return back;
        }

        return bll.selectLabelByArticle(object);
    }
}
