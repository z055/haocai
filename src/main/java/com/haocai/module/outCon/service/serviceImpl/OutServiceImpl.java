package com.haocai.module.outCon.service.serviceImpl;

import com.haocai.module.outCon.dao.OutDao;
import com.haocai.module.outCon.service.OutService;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.outCon.vo.param.ConTypeNameParam;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.outCon.vo.result.ConTypeNameResult;
import com.haocai.module.outCon.vo.result.OutVoResult;
import com.haocai.module.system.vo.User;
import com.haocai.utils.ConversionStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OutServiceImpl implements OutService {
    @Autowired
    private OutDao outDao;

    @Override
    public OutVoResult getData(OutVoParam outVoParam, List<String> roleNameList) {
        outVoParam.setPage((outVoParam.getPage() - 1) * outVoParam.getLimit());
        List<OutConVo> list;
        int count;
        if (roleNameList.contains("库管员") && roleNameList.contains("教师")) {
            list = outDao.getData2(outVoParam);
            count = outDao.getCount2(outVoParam);
        }else{
            list = outDao.getData(outVoParam);
            count = outDao.getCount(outVoParam);
        }
        for (OutConVo i:list) {
            OutVoParam param=outDao.getId(i);
            if (param!=null){
                User user=outDao.getName(param);
                i.setShenHeName(user.getName());
            }else {
                OutVoParam voParam=outDao.getTweId(i);
                User user=outDao.getName(voParam);
                if (user!=null){
                    i.setShenHeName(user.getName());
                }
            }
            User user=outDao.getName(param);
            if (user!=null){
                i.setShenHeName(user.getName());
            }
        }
        OutVoResult outVoResult = new OutVoResult();
        outVoResult.setList(list);
        outVoResult.setCount(count);
        return outVoResult;
    }

    @Override
    public int addReview(Map<String, String> map) {//可优化审核 目前是只能2个人审核
        String[] ids = map.get("id").split(",");
        for (String id : ids) {
            String review_id = UUID.randomUUID().toString(); //审核编号
            String project_name = map.get("project_name");
            //修改出库表状态和添加审核号
//        判断一下是出库还是外界  把出库 或者 外界 状态改变
            if ("外借审核".equals(project_name)) {
                outDao.setSbStatus(id, review_id);
            } else if ("出库审核".equals(project_name)) {
                outDao.setConStatus(id, review_id);
            } else if ("报废审核".equals(project_name)) {
                outDao.setConStatus(id, review_id);
            }
            //添加审核表记录
            OutVoParam outVoParam = new OutVoParam();

            outVoParam.setReview_id(review_id);//设置审核标号
            outVoParam.setProject_name(map.get("project_name"));


            outVoParam.setId(UUID.randomUUID().toString());
            outVoParam.setRoleId(map.get("userId1"));
            outVoParam.setBefore_re(null);
            outVoParam.setAfter_re(map.get("userId2"));
            outVoParam.setStatus("0");
            outVoParam.setRole(map.get("role1"));
            outDao.addReview(outVoParam);//添加第一条审核记录

            outVoParam.setId(UUID.randomUUID().toString());
            outVoParam.setRoleId(map.get("userId2"));
            outVoParam.setBefore_re(map.get("userId1"));
            outVoParam.setAfter_re(null);
            outVoParam.setStatus(null);
            outVoParam.setRole(map.get("role2"));
            outDao.addReview(outVoParam);//添加第二条审核记录
        }

        return 1;
    }

    @Override
    public ConTypeNameResult getConTypeName(ConTypeNameParam conTypeNameParam) {
        ConTypeNameResult result = new ConTypeNameResult();
        conTypeNameParam.setPage((conTypeNameParam.getPage() - 1) * conTypeNameParam.getLimit());
        String conType = conTypeNameParam.getConType();
        if(conType .equals("3") ){
            result.setList(outDao.getCourseName(conTypeNameParam));
            result.setCount(outDao.getCourseCount(conTypeNameParam));
        }else{
            result.setList(outDao.getConTypeName(conTypeNameParam));
            result.setCount(outDao.getConTypeCount(conTypeNameParam));
        }

        return result;
    }

    @Override
    public int addReviewTow(Map<String, String> map) {
        System.err.println(map.toString());
        //插入出库表数据
        OutConVo conData = outDao.getConById(map.get("id"));
        String id = UUID.randomUUID().toString();
        String reviewId = UUID.randomUUID().toString();
        BigDecimal price = new BigDecimal(map.get("price").toString());
        BigDecimal outnumber = new BigDecimal(map.get("outNumber").toString());
        Calendar calendar = Calendar.getInstance();
        String outTime = calendar.get(Calendar.YEAR) + "";
        conData.setNumber(map.get("outNumber"));
        conData.setPrice_z(price.multiply(outnumber).toString());
        conData.setId(id);
        conData.setOutYear(outTime);
        conData.setOut_type(map.get("out_type"));
        conData.setApplicant(map.get("applicant"));
        conData.setApplicantName(map.get("applicantName"));
        conData.setReview_id(reviewId);
        conData.setLibraryName(map.get("role4"));
        conData.setConTypeId(map.get("conTypeId"));
        conData.setConTypeNo(map.get("conTypeNo"));
        outDao.addOutCon(conData);


        //插入审核数据
        List<OutReviewVo> list = new ArrayList<>();
        OutReviewVo outReviewVo = new OutReviewVo();
        outReviewVo.setReview_id(reviewId);
        outReviewVo.setProject_name(map.get("project_name"));

        outReviewVo.setId(UUID.randomUUID().toString());
        outReviewVo.setRe_pe_id(map.get("role1"));
        outReviewVo.setBefore_re(null);
        outReviewVo.setAfter_re(map.get("role2"));
        outReviewVo.setStatus("0");
        outReviewVo.setRole("专业负责人");
        outDao.AddOutConReview(outReviewVo);

        outReviewVo.setId(UUID.randomUUID().toString());
        outReviewVo.setRe_pe_id(map.get("role2"));
        outReviewVo.setBefore_re(map.get("role1"));
        outReviewVo.setAfter_re(map.get("role3"));
        outReviewVo.setStatus(null);
        outReviewVo.setRole("二级学院管理员");
        outDao.AddOutConReview(outReviewVo);

        outReviewVo.setId(UUID.randomUUID().toString());
        outReviewVo.setRe_pe_id(map.get("role3"));
        outReviewVo.setBefore_re(map.get("role2"));
        outReviewVo.setAfter_re(null);
        outReviewVo.setStatus(null);
        outReviewVo.setRole("二级学院教学院长");
        list.add(outReviewVo);
        outDao.AddOutConReview(outReviewVo);
        return 0;
    }

    @Override
    public Integer getReviewSumNumber(String id) {
        OutConVo conData = outDao.getConById(id);
        return outDao.getReviewSumNumber(conData);
    }

    @Override
    public Integer getNumber(String id) {
        return outDao.getNumber(id);
    }

    @Override
    public int del(String[] ids) {
        List list = Arrays.asList(ids);
        List<OutReviewVo> outReviewVos = outDao.getReviewIds(list);
        List reviewIds = new ArrayList();
        for (OutReviewVo outReviewVo : outReviewVos) {
            String reId = outReviewVo.getReview_id();
            reviewIds.add(reId);
        }
        outDao.delReviewById(reviewIds);
        return outDao.del(ids);
    }

    @Override
    public List<OutConVo> outCons(List<OutConVo> outConVos) {
        List<OutConVo> outErrorCons = new ArrayList<>();
        Date date = new Date();
        for (OutConVo outConVo : outConVos) {
            outConVo = (OutConVo)ConversionStrUtil.ConversionStr(outConVo);
            OutConVo consOutCon = outDao.getConNumber(outConVo);
            int number = Integer.parseInt(consOutCon.getNumber());//获取库存数量
            int outNumber = Integer.parseInt(outConVo.getNumber());//出库的数量
            if (number < outNumber) {
                outErrorCons.add(outConVo);
                continue;
            }
            //修改厂库的总价格
            BigDecimal price = new BigDecimal(outConVo.getPrice());
            BigDecimal num = new BigDecimal(outConVo.getNumber());
            BigDecimal outPrices = price.multiply(num);
            BigDecimal prices_z = new BigDecimal(consOutCon.getPrice_z());
            String prices = prices_z.subtract(outPrices).toString();
            outDao.updatePriceCons(consOutCon.getId(), prices);
            outConVo = (OutConVo) ConversionStrUtil.ConversionStr(outConVo);
            outDao.outConNumber(outConVo);//出库

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            outConVo.setOut_con_time(sdf.format(date));
            outDao.setOutStatus(outConVo);//修改出库表状态是已经出库状态，并且添加出库时间
        }
        return outErrorCons;
    }

    @Override
    public OutVoResult getScrapData(OutVoParam outVoParam) {
        outVoParam.setPage((outVoParam.getPage() - 1) * outVoParam.getLimit());
        List<OutConVo> list = outDao.getScrapData(outVoParam);
        for (OutConVo i:list) {
            OutVoParam param=outDao.getId(i);
            if (param!=null){
                User user=outDao.getName(param);
                i.setShenHeName(user.getName());
            }else {
                OutVoParam voParam=outDao.getTweId(i);
                User user=outDao.getName(voParam);
                if (user!=null){
                    i.setShenHeName(user.getName());
                }
            }
            User user=outDao.getName(param);
            if (user!=null){
                i.setShenHeName(user.getName());
            }
        }
        int count = outDao.getCount(outVoParam);
        OutVoResult outVoResult = new OutVoResult();
        outVoResult.setList(list);
        outVoResult.setCount(count);
        return outVoResult;
    }
    @Override
    public int addCheck(Map<String, String> map) {
        System.err.println(map.toString());
        //插入出库表数据
        OutConVo conData=outDao.getConById(map.get("id"));
        String id=UUID.randomUUID().toString();
        String reviewId=UUID.randomUUID().toString();
        BigDecimal price=new BigDecimal(map.get("price").toString());
        BigDecimal outnumber=new BigDecimal(map.get("outNumber").toString());
        Calendar calendar = Calendar.getInstance();
        String outTime = calendar.get(Calendar.YEAR)+"";
        conData.setNumber(map.get("outNumber"));
        conData.setPrice_z(price.multiply( outnumber).toString());
        conData.setId(id);
        conData.setOutYear(outTime);
        conData.setOut_type(map.get("out_type"));
        conData.setApplicant(map.get("applicant"));
        conData.setApplicantName(map.get("applicantName"));
        conData.setReview_id(reviewId);
        conData.setLibraryName(map.get("role4"));
        outDao.addOutCon(conData);

        //插入审核数据
        List<OutReviewVo>  list=new ArrayList<>();
        OutReviewVo outReviewVo=new OutReviewVo();
        outReviewVo.setReview_id(reviewId);
        outReviewVo.setProject_name(map.get("project_name"));

        outReviewVo.setId(UUID.randomUUID().toString());
        outReviewVo.setRe_pe_id(map.get("role2"));
        outReviewVo.setBefore_re(null);
        outReviewVo.setAfter_re(map.get("role3"));
        outReviewVo.setStatus("0");
        outReviewVo.setRole("二级学院管理员");
        outDao.AddOutConReview(outReviewVo);

        outReviewVo.setId(UUID.randomUUID().toString());
        outReviewVo.setRe_pe_id(map.get("role3"));
        outReviewVo.setBefore_re(map.get("role2"));
        outReviewVo.setAfter_re(null);
        outReviewVo.setStatus(null);
        outReviewVo.setRole("二级学院教学副院长");
        list.add(outReviewVo);
        outDao.AddOutConReview(outReviewVo);
        return 0;
    }
}
