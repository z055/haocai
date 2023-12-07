package com.haocai.module.inCon.service.serviceImpl;

import com.haocai.module.inCon.dao.InConAuditDao;
import com.haocai.module.inCon.service.InConAuditService;
import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.InReviewVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.inCon.vo.result.InConVoResult;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.utils.ConversionStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class InConAuditServiceImpl implements InConAuditService {
@Autowired
private InConAuditDao inConAuditDao;
    @Override
    public InConVoResult getData(InConVoParam param) {
        param.setPage((param.getPage()-1)*param.getLimit());
        List<InConVo> list= inConAuditDao.getData(param);

        int count=inConAuditDao.getCount(param);
        InConVoResult result=new InConVoResult();
        result.setList(list);
        result.setCount(count);
        return result;
    }

    @Override
    public int audit(InConVoParam param) {
        String [] ids=param.getId().split(",");
        for (String id : ids) {
            //根据id获取到审核记录的信息方便获取上下审核人
            InConVoParam inReviewVo=inConAuditDao.getDataById(id);
            String role=inReviewVo.getRole();//审核人职位
            String review_id=inReviewVo.getReview_id();//审核标号
            //本人审核通过
            int auto=inConAuditDao.setStatusById(id,"1",param.getAudit_body());
            System.err.println("本人的审核记录修改成功");
            if ("二级学院管理员".equals(role)){
                //更改出库表成二级管理的审核通过状态
                int auto1=inConAuditDao.setStatus("2",review_id);
                System.err.println("出库表审核状态改未二级管理员通过");
            }
            if ("二级学院教学院长".equals(role)||"二级学院教学副院长".equals(role)){
                inConAuditDao.setStatus("4",review_id);
                System.err.println("出库表审核状态改未二级学院教学院长通过");
            }
            if (inReviewVo.getAfter_re()!=null){//有就更改 下一审核人 的审核状态
                //根据信息获得上下审核人   修改下一审核人的状态 待审核状态
                int auto2=inConAuditDao.setStatusByReId("0",inReviewVo.getAfter_re(),review_id);
                System.err.println("还有下一人审核而且已经修改下一任的审核状态为待审核");
            }
            else {//么有就出库表改成最终状态
                inConAuditDao.setShowStatus("4",review_id);
                //根据审核id获取耗材信息
                InConVo inConVo=inConAuditDao.getHaoCaiData(review_id);
                inConVo = (InConVo) ConversionStrUtil.ConversionStr(inConVo);
                //查询库存里面有没有这个耗材
                String conID=inConAuditDao.selectCon(inConVo);
                if (conID!=null){//            有这个耗材添加数量操作
                    inConAuditDao.addConNumber(conID,inConVo.getNumber());
                }
                else {
                    inConAuditDao.addCon(inConVo);
                    inConAuditDao.updateState();
                }
                System.err.println("已经没有下一个审核人了已经吧入库表改为了最终通过状态");
            }
        }
        return 1;
    }

    @Override
    public int rejected(InConVoParam param) {
        String[] ids=param.getId().split(",");
        for (String id : ids) {
            //根据id获取到审核记录的信息方便获取上下审核人
            InConVoParam inReviewVo=inConAuditDao.getDataById(id);
            String role=inReviewVo.getRole();
            String review_id=inReviewVo.getReview_id();
            int auto=inConAuditDao.setStatusById(id,"2",param.getAudit_body());
            //修改出库表的退回状态   如果已经被其他人退会则不会修改
            if ("二级学院管理员".equals(role)){
                //更改出库表成二级管理的审核退回状态
                int auto1=inConAuditDao.setStatus("3",review_id);
                System.err.println("二级学院管理员退回状态修改成");
            }
            if ("二级学院教学院长".equals(role)||"二级学院教学副院长".equals(role)){
                int auto1=inConAuditDao.setStatus("5",review_id);
                System.err.println("二级学院教学院长退回状态修改成");
            }
            //判断还有没有上一个审核人
            if (inReviewVo.getBefore_re()!=null){//有就更改 上一审核人 的审核状态
                //根据用户id和审核标号 修改审核状态未被退回
                int auto2=inConAuditDao.setStatusByReId("3",inReviewVo.getBefore_re(),review_id);
                System.err.println("把上一级审核人状态改为了被退回");
            }
            else {//么有就出库表改成最终状态
                int auto4=inConAuditDao.setShowStatus("2",review_id);//改成显示的退回状态
                System.err.println("没有了上一审核人吧最终显示状态改为退回");
            }
        }

        return 1;
    }

    @Override
    public InConVo getHaoCaiData(String review_id) {
        return inConAuditDao.getHaoCaiData(review_id);
    }

    @Override
    public List<InConVoParam> getHaoCaiDatas(List review_id) {
        List<InConVoParam> list=inConAuditDao.getHaoCaiDatas(review_id);
                for (InConVo resultData : list) {
            switch (resultData.getAttribute()){
                case "1":
                    resultData.setAttribute("低值易耗品");
                    break;
                case "2":
                    resultData.setAttribute("低值耐用品");
                    break;
                default:
                    resultData.setAttribute("其他");
            }

            switch (resultData.getType()){
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
                    resultData.setType("维修保养");
                    break;
                case "10":
                    resultData.setType("其他");
                    break;
                default:
                    resultData.setType("其他的");
            }
            switch (resultData.getCon_type()){
                case "1": resultData.setCon_type("技能大赛");break;
                case "2": resultData.setCon_type("生产实训");break;
                case "3": resultData.setCon_type("基本技能");break;
                case "4": resultData.setCon_type("设备维护保养");break;
            }
        }
        return list;
    }
}
