package com.haocai.module.sbModule.service.serviceImpl;

import com.haocai.module.sbModule.dao.ReviewDao;
import com.haocai.module.sbModule.service.SbReviewService;
import com.haocai.module.sbModule.vo.ConOut;
import com.haocai.module.sbModule.vo.ReviewTable;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.ReviewParam;
import com.haocai.module.sbModule.vo.param.SbParam;
import com.haocai.module.sbModule.vo.resulet.SbResult;
import com.haocai.module.system.vo.Role;
import com.haocai.module.system.vo.User;

import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class SbReviewServiceImpl implements SbReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Override
    public int getAllReviewDataCount(ReviewParam reviewParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        //获取当前用户账号(工号)
        String account = user.getAccount();
        long userId = user.getUserId();
        reviewParam.setRe_pe_id(String.valueOf(userId));
        int resultCount = reviewDao.getAllReviewDataCount(reviewParam);
        return resultCount;
    }

    /**
     * 查询当前用户要审核的数据
     *
     * @param reviewParam
     * @param request
     * @return
     */
    @Override
    public List<ReviewTable> getAllReviewData(ReviewParam reviewParam, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        reviewParam.setPage((reviewParam.getPage() - 1) * reviewParam.getLimit());
        List<ReviewTable> list = reviewDao.getAllReviewData(reviewParam);
        List<ReviewTable> endData = new ArrayList<>();
        for (ReviewTable resultData : list) {
            String name = reviewDao.selectNameByAccount(resultData.getOperator());
            resultData.setOperator(name);
            switch (resultData.getAttribute()) {
                case "1":
                    resultData.setAttribute("低值易耗品");
                    break;
                case "2":
                    resultData.setAttribute("低值耐用品");
                    break;
                default:
                    resultData.setAttribute("其他");
            }

            switch (resultData.getType()) {
                case "1":
                    resultData.setType("酒店食品");
                    break;
                case "2":
                    resultData.setType("金属材料");
                    break;
                case "3":
                    resultData.setType("电子电器");
                    break;
                case "4":
                    resultData.setType("建筑材料");
                    break;
                case "5":
                    resultData.setType("服装服饰");
                    break;
                case "6":
                    resultData.setType("文化绘画");
                    break;
                case "7":
                    resultData.setType("工具仪表");
                    break;
                case "8":
                    resultData.setType("化工药品");
                    break;
                case "9":
                    resultData.setType("气体类");
                    break;
                case "10":
                    resultData.setType("维修保养");
                    break;
                case "11":
                    resultData.setType("其他");
                    break;
                default:
                    resultData.setType("其他的");
            }


            switch (resultData.getStatus()) {
                case "0":
                    resultData.setStatus("待审核");
                    break;
                case "1":
                    resultData.setStatus("通过");
                    break;
                case "2":
                    resultData.setStatus("回退");
                    break;
                case "3":
                    resultData.setStatus("被回退");
                    break;
            }

            endData.add(resultData);
        }
        return endData;
    }


    /**
     * 查询审核详情
     * @param reviewTable
     * @return
     */
    @Override
    public List<ReviewTable> getDetailData(ReviewTable reviewTable) {
        List<ReviewTable> list = reviewDao.getSbDetailData(reviewTable);
        List<ReviewTable> endData = new ArrayList<>();
        for (ReviewTable resultData : list) {
            String name = reviewDao.selectNameByUserId(resultData.getRe_pe_id());
            resultData.setOperator(name);
            endData.add(resultData);
        }
        return endData;
    }

    /**
     * 查询要审核的具体数据 根据审核编号查
     * @param reviewParam
     * @return
     */
    @Override
    public List<SbVo> getReviewBorrowData(ReviewParam reviewParam) {
        List<SbVo> list = reviewDao.getReviewBorrowData(reviewParam);
        List<SbVo> endData = new ArrayList<>();
        for (SbVo resultData : list) {
            switch (resultData.getAttribute()) {
                case "1":
                    resultData.setAttribute("低值易耗品");
                    break;
                case "2":
                    resultData.setAttribute("低值耐用品");
                    break;
                default:
                    resultData.setAttribute("其他");
            }

            switch (resultData.getType()) {
                case "1":
                    resultData.setType("酒店食品");
                    break;
                case "2":
                    resultData.setType("金属材料");
                    break;
                case "3":
                    resultData.setType("电子电器");
                    break;
                case "4":
                    resultData.setType("建筑材料");
                    break;
                case "5":
                    resultData.setType("服装服饰");
                    break;
                case "6":
                    resultData.setType("文化绘画");
                    break;
                case "7":
                    resultData.setType("工具仪表");
                    break;
                case "8":
                    resultData.setType("化工药品");
                    break;
                case "9":
                    resultData.setType("气体类");
                    break;
                case "10":
                    resultData.setType("维修保养");
                    break;
                case "11":
                    resultData.setType("其他");
                    break;
                default:
                    resultData.setType("其他的");

            }
            switch (resultData.getStatus()) {
                case "0":
                    resultData.setStatus("待审核");
                    break;
                case "1":
                    resultData.setStatus("通过");
                    break;
                case "2":
                    resultData.setStatus("回退");
                    break;
                case "3":
                    resultData.setStatus("被回退");
                    break;
            }

            endData.add(resultData);
        }
        return endData;
    }


    /**
     * 审核通过
     *
     * @param reviewParam
     * @return
     */
    @Override
    public int BorrowReviewPass(ReviewParam reviewParam) {
        System.err.println("reviewParam===-->>》" + reviewParam);
        SbParam sbParam = new SbParam();
        reviewParam.setRe_pe_id(reviewParam.getAfter_re());//将下一审核人作为当前审核人
        System.out.println("reviewParam.getAfter_re() = " + reviewParam.getAfter_re());
        sbParam.setReview_id(reviewParam.getReview_id());
        String projectName = reviewParam.getProject_name();
        String role = reviewParam.getRole();
        if (projectName.equals("外借审核")) {
            if (role.contains("二级学院教学")) {
                reviewParam.setStatus("1");//为自己设置审核通过状态
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);//审核后去修改自己的状态 审核流程表

                ReviewParam flagRP = new ReviewParam();
                flagRP.setRole("库管员");
                flagRP.setReview_id(reviewParam.getReview_id());
                flagRP.setProject_name("外借审核");
                String afterReStatus = reviewDao.getStatus(flagRP);//获取审核状态
                if (afterReStatus != null) {//该耗材已出库或已待审核
                    return 1;
                }
                reviewParam.setRole("库管员");//下一审核人职务
                reviewParam.setStatus("0");//为下一级设置待审核状态
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                sbParam.setStatus("5");//为二级学院教学院长设置借用通过(借用维护表)//原来是4，改为5了
                sbParam.setStatus_z("2");//设置射表的最中状态为借用通过（给提交者看）
                int resultConsumBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);//修改维护表 借用表
                if (resultMe > 0 && resultConsumBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else if ("专业负责人".equals(role)) {
                //第一个审核人
                reviewParam.setStatus("1");//为自己设置通过状态

                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                reviewParam.setRole("二级学院管理员");//下一审核人职务
                reviewParam.setStatus("0");//为下一级设置待审核状态
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                sbParam.setStatus("1");//为审核维护表设置流程状态专业负责人借用通过
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (("二级学院管理员").equals(role)) {
                //中间审核人
                reviewParam.setStatus("1");//为自己设置通过状态
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                reviewParam.setRole("二级学院教学");//下一审核人职务
                reviewParam.setStatus("0");//为下一级设置待审核状态
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                sbParam.setStatus("3");//为审核维护表设置流程状态二级学院管理员借用通过
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else if (projectName.equals("归还审核")) {
            if (role.contains("二级学院教学")) {
                reviewParam.setStatus("1");//为自己设置审核通过状态
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);//去修改

                ReviewParam flagRP = new ReviewParam();
                flagRP.setRole("库管员");
                flagRP.setReview_id(reviewParam.getReview_id());
                flagRP.setProject_name("归还审核");
                String afterReStatus = reviewDao.getStatus(flagRP);//获取审核状态
                if (afterReStatus != null) {//该耗材已归还或已待审核
                    return 1;
                }
                reviewParam.setStatus("0");//为下一级设置待审核状态
                reviewParam.setRole("库管员");//下一审核人职位
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                sbParam.setStatus("12");//为二级学院教学院长设置归还通过(借用维护表)
                sbParam.setStatus_z("6");//设置维护表的最中状态为归还通过（给提交者看）
                int resultConsumBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);//修改维护表
                if (resultMe > 0 && resultConsumBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else if ("专业负责人".equals(role)) {
                //上一级不再有审核人为第一个审核人
                reviewParam.setStatus("1");//为自己设置通过状态
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                reviewParam.setRole("二级学院管理员");//下一审核人职务
                reviewParam.setStatus("0");//为下一级设置待审核状态
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                sbParam.setStatus("8");//为审核维护表设置流程状态专业负责人借用通过
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (("二级学院管理员").equals(role)) {
                //上一级和下一级都有审核人
                reviewParam.setStatus("1");//为自己设置通过状态
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                reviewParam.setStatus("0");//为下一级设置待审核状态
                reviewParam.setRole("二级学院教学");//下一审核人职务
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                sbParam.setStatus("10");//为审核维护表设置流程状态二级学院管理员归还通过
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    /**
     * 审核退回
     *
     * @param reviewParam
     * @return
     */
    @Override
    public int BorrowReviewBack(ReviewParam reviewParam) {
        SbParam sbParam = new SbParam();
        ConOut conOut = new ConOut();
        System.err.println("reviewParam--->>》》》" + reviewParam);
        reviewParam.setRe_pe_id(reviewParam.getBefore_re());
        sbParam.setReview_id(reviewParam.getReview_id());
        String projectName = reviewParam.getProject_name();
        String role = reviewParam.getRole();

        SbVo sbVo = new SbVo();
        List<SbVo> reviewBorrowData = reviewDao.getReviewBorrowData(reviewParam);//
        for (SbVo reviewBorrowDatum : reviewBorrowData) {
            sbVo = reviewBorrowDatum;
        }
        System.err.println("Aaaaaaaaaaaa==>"  + sbVo);

        //审核数据状态
        String oldStatus = reviewParam.getStatus();
        if (projectName.equals("外借审核")) {
            if (reviewParam.getBefore_re() == null || reviewParam.getBefore_re() == "") {
                //专业负责人
//                if (oldStatus.equals("待审核")) {
                //修改自己的状态为回退状态（2）
                reviewParam.setStatus("2");
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                //为借用维护表设置专业负责人借用退回状态
                sbParam.setStatus("2");//原始为3
                //设置最终被回退状态给提交者看(3)
                sbParam.setStatus_z("3");//外借退回
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                int resultOpinion = reviewDao.addReviewOpinion(reviewParam);//添加审核意见
                reviewDao.addReviewTableOpinion(reviewParam);//向审核表添加审核意见
                conOut.setStatus_z("1");
                conOut.setBorrow_review_id(reviewParam.getReview_id());
                reviewDao.updateOutStatus(conOut);//设置出库表最终审核状态 被退回
                if (resultMe > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
                /*} else if (oldStatus.equals("被回退")) {
                    //修改自己的状态为回退
                    reviewParam.setStatus("2");
                    int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                    //修改目录最终状态为被退回
                    sbParam.setStatus("2");//专业负责人退回
                    sbParam.setStatus_z("3");//外借退回
                    int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                    if (resultMe > 0 && resultBorrow > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }*/
            } else if (("二级学院管理员").equals(role)) {
                //第二级
                //将自己的状态改为回退
//                if (oldStatus.equals("待审核")) {
                reviewParam.setStatus("2");
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);//自己退回
                //修改上一级的状态为被回退
                reviewParam.setStatus("3");
                reviewParam.setRole("专业负责人");//上一审核人职务
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);//上一级被退回
                //修改借用维护表的状态为二级学院管理员借用退回状态
                sbParam.setStatus("4");
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                int resultOpinion = reviewDao.addReviewOpinion(reviewParam);//添加审核意见
                reviewDao.addReviewTableOpinion(reviewParam);//向审核表添加审核意见
                conOut.setStatus_z("1");
                conOut.setBorrow_review_id(reviewParam.getReview_id());
                reviewDao.updateOutStatus(conOut);//设置出库表最终审核状态 被退回
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
               /* } else if (oldStatus.equals("被回退")) {
                    //修改自己的状态为回退
                    reviewParam.setStatus("2");
                    int resultMe = reviewDao.reviewUpdateMe(reviewParam);//自己退回
                    reviewParam.setStatus("3");
                    int resultOther = reviewDao.reviewUpdateOther(reviewParam);//上一级被退回
                    sbParam.setStatus("4");
                    int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);//借用表二级管理员退回
                    if (resultMe > 0 && resultBorrow > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }*/
         /*       reviewParam.setStatus("2");
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                //修改上一级的状态为被回退
                reviewParam.setStatus("3");
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                //修改借用维护表的状态为二级学院教学院长借用退回状态
                sbParam.setStatus("5");
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }*/
            } else if (("二级学院教学副院长").equals(role)) {
//                if (oldStatus.equals("待审核")) {
                reviewParam.setStatus("2");
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);//自己退回
                //修改上一级的状态为被回退
                reviewParam.setStatus("3");
                reviewParam.setRole("二级学院管理员");//上一审核人职务
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);//上一级被退回
                //修改借用维护表的状态为二级学院教学院长借用退回状态
                sbParam.setStatus("6");
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                int resultOpinion = reviewDao.addReviewOpinion(reviewParam);//添加审核意见
                reviewDao.addReviewTableOpinion(reviewParam);//向审核表添加审核意见
                conOut.setStatus_z("1");
                conOut.setBorrow_review_id(reviewParam.getReview_id());
                reviewDao.updateOutStatus(conOut);//设置出库表最终审核状态 被退回
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
//                }
            }

        } else if (projectName.equals("归还审核")) {
            if (reviewParam.getBefore_re() == null || reviewParam.getBefore_re() == "") {
                //专业负责人
//                if (oldStatus.equals("待审核")) {
                //修改自己的状态为回退状态（2）
                reviewParam.setStatus("2");//自己退回
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                //为借用维护表设置二级学院管理员归还退回状态
                sbParam.setStatus("9");//借用表 专业负责人退回
                //设置最终被回退状态给提交者看(6)
                sbParam.setStatus_z("5");//归还退回
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                int resultOpinion = reviewDao.addReviewOpinion(reviewParam);//添加审核意见
                reviewDao.addReviewTableOpinion(reviewParam);//向审核表添加审核意见
                if (resultMe > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }

                /*} else if (oldStatus.equals("被回退")) {
                    //修改自己的状态为回退
                    reviewParam.setStatus("2");
                    int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                    //修改目录最终状态为被回退
                    sbParam.setStatus_z("5");
                    int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                    if (resultMe > 0 && resultBorrow > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }*/
            } else if (("二级学院管理员").equals(role)) {
                //第二级
                //将自己的状态改为回退
                reviewParam.setStatus("2");
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                //修改上一级的状态为被回退
                reviewParam.setStatus("3");
                reviewParam.setRole("专业负责人");//上一审核人职务
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                //修改借用维护表的状态为二级学院管理员归还退回状态
                sbParam.setStatus("11");//二级学院管理员退回
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                int resultOpinion = reviewDao.addReviewOpinion(reviewParam);
                reviewDao.addReviewTableOpinion(reviewParam);//向审核表添加审核意见
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (("二级学院教学副院长").equals(role)) {
                //第二级
                //将自己的状态改为回退
                reviewParam.setStatus("2");
                int resultMe = reviewDao.reviewUpdateMe(reviewParam);
                //修改上一级的状态为被回退
                reviewParam.setStatus("3");
                reviewParam.setRole("二级学院管理员");//上一审核人职务
                int resultOther = reviewDao.reviewUpdateOther(reviewParam);
                //修改借用维护表的状态为二级学院管理员归还退回状态
                sbParam.setStatus("13");//二级学院教学院长退回
                int resultBorrow = reviewDao.reviewUpdateConsumListBorrow(sbParam);
                int resultOpinion = reviewDao.addReviewOpinion(reviewParam);
                reviewDao.addReviewTableOpinion(reviewParam);//向审核表添加审核意见
                if (resultMe > 0 && resultOther > 0 && resultBorrow > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    /**
     * 查看审核意见
     * @param reviewParam
     * @return
     */
    @Override
    public List<ReviewParam> showOpinion(ReviewParam reviewParam) {
        List<ReviewParam> reviewParams = reviewDao.showOpinion(reviewParam);
        return reviewParams;
    }
}
