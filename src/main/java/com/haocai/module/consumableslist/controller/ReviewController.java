package com.haocai.module.consumableslist.controller;


import com.haocai.module.consumableslist.param.ReviewParam;
import com.haocai.module.consumableslist.result.ConsumableslistResult;
import com.haocai.module.consumableslist.result.CourseDataResult;
import com.haocai.module.consumableslist.result.ReviewResult;
import com.haocai.module.consumableslist.service.ReviewService;

import com.haocai.module.consumableslist.util.JsonResult;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.ReviewTable;
import com.haocai.module.system.vo.User;
import org.apache.poi.ddf.EscherSerializationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import com.haocai.module.consumableslist.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 目录审核controller层
 */
@Controller
@RequestMapping("/consumableslistreview")
public class ReviewController {

    @Autowired
    ReviewService reviewService;


    /**
     * 查询学院下实训项目
     * @param reviewParam
     * @return
     */
    @RequestMapping("/getTrainingByCourseData")
    @ResponseBody
    public JsonResult getTrainingByCourseData(ReviewParam reviewParam){
        List<CourseDataResult> result=reviewService.getTrainingByCourseData(reviewParam);
        if (result.size()!=0){
            return new JsonResult(JsonResult.SUCCESS,result,"查询成功");
        }else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }
    }
    /**
     * 查询当前审核人需要审核的全部数据
     * @param reviewParam
     * @param request
     * @return
     */
    @RequestMapping("/getAllReviewData")
    @ResponseBody
    public JsonResult getAllReviewData(ReviewParam reviewParam, HttpServletRequest request){
        System.out.println("1.获取reviewParam参数:");
        System.out.println(reviewParam);
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getUserId();
        reviewParam.setRe_pe_id(String.valueOf(userId));
        String training=reviewParam.getTraining();
        if (training==null){
            training="";
        }
        int resultCount=0;
        List<ReviewTable> resultData=new ArrayList<>();
        if (training.equals("")){
            System.out.println("--------走了简单查询---------");
            resultData=reviewService.getAllReviewData(reviewParam,request);
            resultCount=reviewService.getAllReviewDataCount(reviewParam,request);
        }else {
            resultCount=reviewService.getAllReviewDataCount(reviewParam,request);
            reviewParam.setTraining(training);
            resultData=reviewService.getAllReviewData(reviewParam,request);
        }
        if (resultCount!=0){
            ReviewResult result=new ReviewResult();
            result.setCount(resultCount);
            result.setList(resultData);
            return new JsonResult(JsonResult.SUCCESS,result,"查询成功");
        }else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }

    }

    /**
     * 审核时查询要审核的数据
     * @param reviewParam
     * @return
     */
    @RequestMapping("/getReviewData")
    @ResponseBody
    public JsonResult getReviewData(ReviewParam reviewParam){
        List<Consumableslist> resultData=reviewService.getReviewData(reviewParam);
        if (resultData!=null){
            ConsumableslistResult consumableslistResult=new ConsumableslistResult();
            consumableslistResult.setCount(1);
            consumableslistResult.setList(resultData);
            return new JsonResult(JsonResult.SUCCESS,consumableslistResult,"查询成功");
        }
        return new JsonResult(JsonResult.FALL, "", "无数据");
    }

    /**
     * 根据学院查询本学院专业
     * @param reviewParam
     * @return
     */
    @RequestMapping("/getCourseMajorData")
    @ResponseBody
    public JsonResult getCourseMajorData(ReviewParam reviewParam){
        System.out.println("getCourseMajorData接收的参数");
        System.out.println(reviewParam);
        List<CourseDataResult> result=reviewService.getCourseMajorData(reviewParam);
        if (result.size()!=0){
            return new JsonResult(JsonResult.SUCCESS,result,"查询成功");
        }else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }

    }




    /**
     * 审核通过
     * @param reviewParam
     * @return
     */
    @RequestMapping("/reviewPass")
    @ResponseBody
    public JsonResult reviewPass(ReviewParam reviewParam){
        int resultNum=reviewService.reviewPass(reviewParam);
        if (resultNum>0){
            return new JsonResult(JsonResult.SUCCESS,"","审核通过");
        }else {
            return new JsonResult(JsonResult.FALL, "", "通过失败");
        }

    }

    /**
     * 审核退回
     * @param reviewParam
     * @return
     */
    @RequestMapping("/reviewBack")
    @ResponseBody
    public JsonResult reviewBack(ReviewParam reviewParam){
        int resultNum=reviewService.reviewBack(reviewParam);
        if (resultNum>0){
            return new JsonResult(JsonResult.SUCCESS,"","审核通过");
        }else {
            return new JsonResult(JsonResult.FALL, "", "退回失败");
        }
    }


}
