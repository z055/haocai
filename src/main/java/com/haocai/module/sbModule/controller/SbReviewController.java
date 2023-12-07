package com.haocai.module.sbModule.controller;

import com.haocai.module.sbModule.service.SbReviewService;
import com.haocai.module.sbModule.vo.ReviewTable;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.ReviewParam;
import com.haocai.module.sbModule.vo.param.SbParam;
import com.haocai.module.sbModule.vo.resulet.ReviewResult;
import com.haocai.module.sbModule.vo.resulet.SbResult;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sbModuleReview")
public class SbReviewController {

    @Autowired
    SbReviewService sbReviewService;


    /**
     * 查询要审核的数据
     */
    @RequestMapping("/getAllSbReviewData")
    @ResponseBody
    public JsonResult getAllReviewData(ReviewParam reviewParam, HttpServletRequest request){

        int resultCount= sbReviewService.getAllReviewDataCount(reviewParam,request);
        List<ReviewTable> resultData= sbReviewService.getAllReviewData(reviewParam,request);
        System.err.println("/getAllSbReviewData要审核的数据===》" + resultData.toString());
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
     * 查询要修改的数据
     * @param reviewParam
     * @return
     */
    @RequestMapping("/getReviewBorrowData")
    @ResponseBody
    public JsonResult getReviewBorrowData(ReviewParam reviewParam){
        List<SbVo> resultData=sbReviewService.getReviewBorrowData(reviewParam);
        if (resultData!=null){
            SbResult result=new SbResult();
            result.setCount(1);
            result.setList(resultData);
            return new JsonResult(JsonResult.SUCCESS,result,"查询成功");
        }else {
            return new JsonResult(JsonResult.FALL, "", "无数据");
        }

    }

    /**
     * 查询审核详情
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public Object getDetail(ReviewTable reviewTable) {
        List<ReviewTable> resultData= sbReviewService.getDetailData(reviewTable);
        if (resultData!=null){
            return new JsonTemplate("查询成功", resultData, 0).toString();
        }else {
            return new JsonTemplate("查询失败", "", 0).toString();
        }
    }

    /**
     * 审核通过
     * @param reviewParam
     * @return
     */
    @RequestMapping("/BorrowReviewPass")
    @ResponseBody
    public JsonResult BorrowReviewPass(ReviewParam reviewParam){
        int resultNum=sbReviewService.BorrowReviewPass(reviewParam);
        if (resultNum>0){
            return new JsonResult(JsonResult.SUCCESS,"","审核通过");
        }else{
            return new JsonResult(JsonResult.FALL, "", "审核失败");
        }
    }


    /**
     * 审核退回
     * @param reviewParam
     * @return
     */
    @RequestMapping("/BorrowReviewBack")
    @ResponseBody
    public JsonResult BorrowReviewBack(ReviewParam reviewParam){
        int resultNum=sbReviewService.BorrowReviewBack(reviewParam);
        if (resultNum>0){
            return new JsonResult(JsonResult.SUCCESS,"","审核退回");
        }else {
            return new JsonResult(JsonResult.FALL, "", "审核失败");
        }
    }


    @RequestMapping("/showOpinion")
    @ResponseBody
    public JsonResult showOpinion(ReviewParam reviewParam){
        List<ReviewParam> reviewParams = sbReviewService.showOpinion(reviewParam);
            return new JsonResult(JsonResult.SUCCESS,reviewParams,"查看审核意见成功");
    }

}
