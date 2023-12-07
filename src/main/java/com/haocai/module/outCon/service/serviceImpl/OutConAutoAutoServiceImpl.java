package com.haocai.module.outCon.service.serviceImpl;

import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.outCon.dao.OutConAutoDao;
import com.haocai.module.outCon.service.OutConAutoService;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.OutVoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OutConAutoAutoServiceImpl implements OutConAutoService {
    @Autowired
    private OutConAutoDao outConAutoDao;

    @Override
    public OutVoResult getData(OutVoParam param) {
        param.setPage((param.getPage() - 1) * param.getLimit());
        List<OutConVo> list = outConAutoDao.getData(param);
        OutVoResult result = new OutVoResult();
        result.setCount(outConAutoDao.getCount(param));
        result.setList(list);
        return result;
    }

    @Override
    public OutConVo getHaoCaiData(String review_id) {
        return outConAutoDao.getHaoCaiData(review_id);
    }

    @Override
    public List<InConVoParam> getHaoCaiDatas(List review_id) {
        return outConAutoDao.getHaoCaiDatas(review_id);
    }

    @Override
    public List<InConVoParam> showOption(String review_id) {
        return outConAutoDao.showOption(review_id);
    }

    //审核通过
    @Override
    public int audit(OutVoParam param) {
        String[] ids = param.getId().split(",");
        for (String id : ids) {
            //根据id获取到审核记录的信息方便获取上下审核人
            OutReviewVo outReviewVo = outConAutoDao.getDataById(id);
            String role = outReviewVo.getRole();
            String review_id = outReviewVo.getReview_id();
            //审核通过审核记录表
            int auto = outConAutoDao.setStatusById(outReviewVo.getId(), "1", param.getAudit_body());
            System.err.println("本人的审核记录修改成功");
            if ("二级学院管理员".equals(role)) {
                //更改出库表成二级管理的审核通过状态
                int auto1 = outConAutoDao.setStatus("3", review_id);
                System.err.println("出库表审核状态改未二级管理员通过");
            }else if ("专业负责人".equals(role)) {
                int auto1 = outConAutoDao.setStatus("1", review_id);
                System.err.println("出库表审核状态改未二级管理员通过");
            }else if ("二级学院教学院长".equals(role)) {
                outConAutoDao.setStatus("5", review_id);
                System.err.println("出库表审核状态改未二级学院教学院长通过");
            }
            //判断还有没有下一个审核人
            if (outReviewVo.getAfter_re() != null) {//有就更改 下一审核人 的审核状态
                //根据信息获得上下审核人   修改下一审核人的状态 待审核状态
                int auto2 = outConAutoDao.setStatusByReId("0", outReviewVo.getAfter_re(), review_id);
                System.err.println("还有下一人审核而且已经修改下一任的审核状态为待审核");
            } else {//没有了有就出库表改成最终状态
                outConAutoDao.setShowStatus("2", review_id);
                System.err.println("已经没有下一个审核人了 更改显示最终通过状态");
            }
        }
        return 1;
    }

    //审核驳回
    @Override
    public int rejected(OutVoParam param) {
        String[] ids = param.getId().split(",");
        for (String id : ids) {
            //根据id获取到审核记录的信息方便获取上下审核人
            OutReviewVo outReviewVo = outConAutoDao.getDataById(id);
            String role = outReviewVo.getRole();
            String review_id = outReviewVo.getReview_id();
            int auto = outConAutoDao.setStatusById(outReviewVo.getId(), "2", param.getAudit_body());
            //修改出库表的退回状态   如果已经被其他人退会则不会修改
            if ("二级学院管理员".equals(role)) {
                //更改出库表成二级管理的审核退回状态
                int auto1 = outConAutoDao.setStatus("4", review_id);
                System.err.println("二级学院管理员退回状态修改成");
                auto++;
            } else if ("专业负责人".equals(role)) {
                int auto1 = outConAutoDao.setStatus("2", review_id);
            } else if ("二级学院教学院长".equals(role)) {
                int auto1 = outConAutoDao.setStatus("6", review_id);
                System.err.println("二级学院教学院长退回状态修改成");
                auto++;
            }
            //判断还有没有上一个审核人
            if (outReviewVo.getBefore_re() != null) {//有就更改 上一审核人 的审核状态
                //根据用户id和审核标号 修改审核状态未被退回
                int auto2 = outConAutoDao.setStatusByReId("3", outReviewVo.getBefore_re(), review_id);
                System.err.println("把上一级审核人状态改为了被退回");
                auto++;
            } else {//没有就出库表改成最终状态
                int auto4 = outConAutoDao.setShowStatus("1", review_id);//改成显示的退回状态
                System.err.println("没有了上一审核人吧最终显示状态改为退回");
            }
        }
        return 1;
    }

}
