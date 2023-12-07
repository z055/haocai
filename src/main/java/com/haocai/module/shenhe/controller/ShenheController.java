package com.haocai.module.shenhe.controller;



import com.haocai.module.shenhe.entity.ReviewTable;
import com.haocai.module.shenhe.service.ShenheService;
import com.haocai.module.system.service.DeptService;
import com.haocai.utils.JsonTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("shenhe")
public class ShenheController {

    @Autowired
    private ShenheService shenheService;

    @RequestMapping("insert")
    @ResponseBody
    public String insertShenhe (@RequestParam("list") String data) {
        int index = 0;
        data = data.replace("\"", "");
        data = data.replace("{", "");
        data = data.replace("}", "");
        data = data.replace("[", "");
        data = data.replace("reviewId:", "");
        data = data.replace("projectName:", "");
        data = data.replace("userId:", "");
        data = data.replace("roleName:", "");

        System.out.println("+++++++++++++++++data"+data);
        String[] arr1 = data.split("]");
        String [] reviewId = {};
        String [] projectName= {};
        String [] user = {};
        String [] role = {};
        for (int i = 0; i < arr1.length; i++) {
            System.out.println("arr1="+arr1[i]);
            arr1[i] = arr1[i].replaceAll("^,*","");
            switch (i) {
                case 0:
                    reviewId = arr1[i].split(",");
                    break;
                case 1:
                    projectName = arr1[i].split(",");
                    break;
                case 2:
                    user = arr1[i].split(",");
                    break;
                case 3:
                    role = arr1[i].split(",");
                    break;
            }
        }
//        for (int i = 0; i < 3; i++) {
//            System.out.println(reviewId[i]+"||"+projectName[i]+"||"+user[i]+"||"+role[i]);
//        }

        index = shenheService.insertShenhe(reviewId,projectName,user,role);
        if(index>=3)
           return new JsonTemplate("shenhe","提交成功",1).toString();
        return new JsonTemplate("shenhe","提交失败",0).toString();
    }
}
