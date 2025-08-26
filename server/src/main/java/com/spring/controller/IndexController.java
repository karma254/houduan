package com.spring.controller;


import net.jntoo.db.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController{

    // 首页
    @RequestMapping(value = {"/" , "index"})
    public String Index()
    {
                    
            ArrayList<HashMap> bhtList = Query.make("lunbotu").order("id desc").limit(5).select();
            assign("bhtList" , bhtList);
            
            
            ArrayList<HashMap> jingdianxinxilist1 = Query.make("jingdianxinxi").limit(4).order("liulanliang desc").select();
            assign("jingdianxinxilist1" , jingdianxinxilist1);
            
            
            ArrayList<HashMap> difangfandianlist2 = Query.make("difangfandian").limit(4).order("id desc").select();
            assign("difangfandianlist2" , difangfandianlist2);


            ArrayList<HashMap> xinwenxinxilist3 = Query.make("xinwenxinxi").limit(4).order("id desc").select();
            assign("xinwenxinxilist3" , xinwenxinxilist3);
                            if(isAjax())
        {
            return json();
        }
        return "index";
        
    }



}
