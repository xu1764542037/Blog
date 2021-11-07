package com.laoxu.blog.controller.impl;

import com.laoxu.blog.bll.AbsSuperService;
import com.laoxu.blog.bll.impl.DraftServiceImpl;
import com.laoxu.blog.bll.impl.LabelServiceImpl;
import com.laoxu.blog.controller.AbsSuperController;
import com.laoxu.blog.entity.BackReturn;
import com.laoxu.blog.entity.Draft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value ="/draft",method = RequestMethod.POST)
public class DraftControllerImpl extends AbsSuperController {
    @Autowired
    private DraftServiceImpl bll;

    @Autowired
    private Draft draft;

    @Autowired
    private LabelServiceImpl labelService;

    @Override
    public AbsSuperService getService() {
        setModel(draft);
        return bll;
    }

    @Override
    @CrossOrigin
    @RequestMapping(value = "/addDraft",method = RequestMethod.POST)
    @ResponseBody
    public BackReturn add(@RequestBody Map<String, Object> object) {
        String id = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());

        draft.setId(id);
        draft.setName(object.get("name").toString());
        draft.setSummary(object.get("summary").toString());
        draft.setText(object.get("text").toString());
        draft.setType(object.get("type").toString());
        draft.setState(object.get("state").toString());

        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        String StartTime = ""+year+"-"+month+"-"+day+"";
        draft.setStartTime(StartTime);
        draft.setEndTime(StartTime);


//        Map<String,Object> map = new HashMap<>();
//        map.put("id",(Integer) object.get("id"));
//        if ( iArticle.select(map) == null) {
//        }
        BackReturn back = bll.add(draft);

        draft.setId(id);
        List label_ids = (List) object.get("labels");
        labelService.setLabelByDraft(draft,label_ids);

        return back;
    }

    @CrossOrigin
    @RequestMapping(value = "/selectAllDraft", method = RequestMethod.POST)
    @ResponseBody
    public BackReturn selectAllDraft(@RequestBody Map<String,Object> object) {
        BackReturn back = bll.selectAllDraft(object);
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
