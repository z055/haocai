package com.haocai.module.sbModule.service.serviceImpl;

import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.sbModule.dao.ReviewDao;
import com.haocai.module.sbModule.dao.SbDao;
import com.haocai.module.sbModule.service.SbService;
import com.haocai.module.sbModule.util.ReDateUtil;
import com.haocai.module.sbModule.vo.ConOut;
import com.haocai.module.sbModule.vo.ReviewTable;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.ReviewParam;
import com.haocai.module.sbModule.vo.param.SbParam;
import com.haocai.module.sbModule.vo.resulet.SbResult;
import com.haocai.module.system.vo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class SbServiceImpl implements SbService {
    @Autowired
    private SbDao sbDao;
    @Autowired
    ReviewDao reviewDao;

    /**
     * 获取出库表单个耗材的数量
     *
     * @param map
     * @return
     */
    @Override
    public int getConOutNum(Map<String, String> map) {
        System.err.println("alllllllllllalasdanfwfhhfaaaaaaaaaaaa==>" + map);
        ConOut conOut = new ConOut();
        String type = map.get("type");
        ReDateUtil reDateUtil = new ReDateUtil();
        type = reDateUtil.reType(type);
        conOut.setType(type);
        conOut.setName(map.get("name"));
        String attribute = map.get("attribute");
        attribute = reDateUtil.reAttribute(attribute);
        String conType = map.get("conType");
        conType = reDateUtil.reConType(conType);
        conOut.setAttribute(attribute);
        conOut.setPrice(map.get("price"));
        conOut.setDepartment(map.get("department"));
        conOut.setStandards(map.get("standards"));
        List<ConOut> resList = sbDao.getConOut(conOut);
        int conOutNum = 0;
//        BigDecimal conOutNumber = new BigDecimal("0");
        for (ConOut lConOut :
                resList) {
            if (lConOut.getStatus_z().equals("0") || lConOut.getStatus_z().equals("2")) {
                System.err.println("getConOutNum==>" + lConOut.getNumber());
                String number = lConOut.getNumber();
//                BigDecimal number = new BigDecimal(lConOut.getNumber());
//                int num = Integer.parseInt(number.substring(0, number.lastIndexOf(".")));
                int num  = Integer.parseInt(number);
//                conOutNumber = number.add(conOutNumber);
                conOutNum = conOutNum + num;
            }
        }
//        int conOutNum = 0;
//        if (conOutNumStr != null) {
//            conOutNum = Integer.parseInt(conOutNumStr.split(".")[0]);
//        }
//        String a = String.valueOf(conOutNumber);
//        conOutNum = In
        return conOutNum;
    }

    @Override
    public SbResult getBorrows(SbParam sbVo) {
        sbVo.setPage((sbVo.getPage() - 1) * sbVo.getLimit());
        String type = sbVo.getType();//耗材类别:酒店食品1、金属材料2、电子电器3、建筑材料4、服装服饰5、文化绘画6、工具仪表7、化工药品8、气体类9、维修保养10、其他11
        ReDateUtil reDateUtil = new ReDateUtil();
        type = reDateUtil.reType(type);
        sbVo.setType(type);
        System.out.println("sbVo----->>》》》" + sbVo);
        List<SbVo> list = sbDao.getBorrows(sbVo);
        int count = sbDao.getCount(sbVo);
        SbResult result = new SbResult();
        result.setCount(count);
        result.setList(list);
        return result;
    }

    public int addSb(Map<String, String> map) {
        ReDateUtil reDateUtil = new ReDateUtil();
        System.err.println("/addSB.addSB()---->" + map.toString());
        //插入借用表数据
        SbVo sbVo = sbDao.getConById(map.get("id"));
        System.err.println("addSB.addSB().sbVo====>" + sbVo);
        String id = UUID.randomUUID().toString();
        String reviewId = UUID.randomUUID().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        BigDecimal price = new BigDecimal(map.get("price"));
        BigDecimal outnumber = new BigDecimal(map.get("outNumber"));
        Calendar calendar = Calendar.getInstance();
        String sbTime = calendar.get(Calendar.YEAR) + "";
        sbVo.setNumber(map.get("outNumber"));
        sbVo.setPrices(price.multiply(outnumber).toString());
        sbVo.setId(id);
        sbVo.setSbYear(sbTime);
        sbVo.setSb_time(time);
        sbVo.setConType(map.get("out_type"));//库类别
        sbVo.setOperator(map.get("operator"));//操作人
        sbVo.setReview_id(reviewId);
        sbVo.setLibraryName(map.get("role4"));
        System.err.println("addSB.addSB().sbVo1====>" + sbVo);
        sbDao.addSb(sbVo);

        //插入出库表数据
        String idOut = UUID.randomUUID().toString();
        String reviewIdOut = UUID.randomUUID().toString();
        BigDecimal priceOut = new BigDecimal(map.get("price"));
        BigDecimal outnumberOut = new BigDecimal(map.get("outNumber"));
        ConOut conOut = new ConOut();
        conOut.setId(idOut);
        conOut.setReview_id(reviewIdOut);

        conOut.setNumber(map.get("outNumber"));
        conOut.setDepartment(map.get("department"));//二级学院
        conOut.setPrice(map.get("price"));

        String attribute = map.get("attribute");
        attribute = reDateUtil.reAttribute(attribute);
        conOut.setAttribute(attribute);//耗材属性

        String type = map.get("type");
        type = reDateUtil.reType(type);
        conOut.setType(type);

        conOut.setStatus("0");//审核状态  0审核中 1 专业负责人同意 2专业负责人驳回  3二级管理员同意 4二极管理员退回 5二级学院教学院长通过 6二级学院教学院长 退回
        conOut.setStatus_z("0");//0审核中 1被推回 2通过  3出库完成
        conOut.setApplicant(map.get("operator"));
        conOut.setOutYear(sbTime);
        conOut.setOut_time(time);
        String conType = map.get("conType");
        if (conType.equals("技能大赛")) {
            conType = "1";
        } else if (conType.equals("生产实训")) {
            conType = "2";
        } else if (conType.equals("基本技能")) {
            conType = "3";
        }
        conOut.setCon_type(conType);
        conOut.setName(map.get("consumableName"));
        conOut.setOut_type("3");//出库类型 3外借
        conOut.setStandards(map.get("standards"));//耗材规格
        conOut.setPrices(String.valueOf(outnumber.multiply(priceOut)));
        conOut.setLibraryName(map.get("role4"));
        conOut.setBorrow_review_id(reviewId);//出库表与外借审核表的唯一联系
        System.err.println("addSB.addSB().conOut===========>" + conOut);
        sbDao.addOut(conOut);

        //插入审核数据
        List<ReviewTable> list = new ArrayList<>();
        ReviewTable reviewTable = new ReviewTable();
        reviewTable.setReview_id(reviewId);
        reviewTable.setProject_name(map.get("project_name"));
        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role1"));
        reviewTable.setBefore_re(null);
        reviewTable.setAfter_re(map.get("role2"));
        reviewTable.setStatus("0");
        reviewTable.setRole("专业负责人");
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role2"));
        reviewTable.setBefore_re(map.get("role1"));
        reviewTable.setAfter_re(map.get("role3"));
        reviewTable.setStatus(null);
        reviewTable.setRole("二级学院管理员");
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role3"));
        reviewTable.setBefore_re(map.get("role2"));
        reviewTable.setAfter_re(map.get("role4"));
        reviewTable.setStatus(null);
        reviewTable.setRole("二级学院教学副院长");
        list.add(reviewTable);
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role4"));
        reviewTable.setBefore_re(map.get("role3"));
        reviewTable.setAfter_re(null);
        reviewTable.setStatus(null);
        reviewTable.setRole("库管员");
        list.add(reviewTable);
        sbDao.AddSbReview(reviewTable);
        return 0;
    }

    @Override
    public int addGiveBackSb(Map<String, String> map) {
        System.err.println("/addGiveBackSb.addGiveBackSb()---->" + map.toString());
        //插入借用表数据
//        SbVo sbVo = sbDao.getConById(map.get("id"));
//        String id = UUID.randomUUID().toString();
//        String reviewId = UUID.randomUUID().toString();
        String review_id = map.get("review_id");
//        BigDecimal price = new BigDecimal(map.get("price"));
//        BigDecimal number = new BigDecimal(map.get("number"));
//        System.err.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + number);
        Calendar calendar = Calendar.getInstance();
//        String sbTime = calendar.get(Calendar.YEAR) + "";
//        String number1 = map.get("number");
        SbVo sbVo = new SbVo();
//        sbVo.setNumber(number1);
//        sbVo.setPrices(price.multiply(number).toString());
//        sbVo.setId(id);
//        sbVo.setSbYear(sbTime);
        sbVo.setStatus("7");//归还审核中
        sbVo.setStatus_z("4");//归还审核中
//        sbVo.setConType(map.get("out_type"));
//        sbVo.setOperator(map.get("operator"));//操作人
        sbVo.setReview_id(review_id);
        System.err.println(sbVo);
        sbVo.setLibraryName(map.get("role4"));
        sbDao.updateSbBorrow(sbVo);

        //插入审核数据
        List<ReviewTable> list = new ArrayList<>();
        ReviewTable reviewTable = new ReviewTable();
        reviewTable.setReview_id(review_id);
        reviewTable.setProject_name(map.get("project_name"));
        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role1"));
        reviewTable.setBefore_re(null);
        reviewTable.setAfter_re(map.get("role2"));
        reviewTable.setStatus("0");
        reviewTable.setRole("专业负责人");
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role2"));
        reviewTable.setBefore_re(map.get("role1"));
        reviewTable.setAfter_re(map.get("role3"));
        reviewTable.setStatus(null);
        reviewTable.setRole("二级学院管理员");
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role3"));
        reviewTable.setBefore_re(map.get("role2"));
        reviewTable.setAfter_re(map.get("role4"));
        reviewTable.setStatus(null);
        reviewTable.setRole("二级学院教学副院长");
        list.add(reviewTable);
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role4"));
        reviewTable.setBefore_re(map.get("role3"));
        reviewTable.setAfter_re(null);
        reviewTable.setStatus(null);
        reviewTable.setRole("库管员");
        list.add(reviewTable);
        sbDao.AddSbReview(reviewTable);
        return 0;
    }

    @Override
    public int delSb(SbParam sbParam) {
        sbDao.delSb(sbParam);
        sbDao.deleteReviewData(sbParam);
        return 0;
    }

    @Override
    public int updateSbReview(Map<String, String> map) {
        System.err.println(map.toString());
        //插入出库表数据
        SbVo sbVo = sbDao.getSbById(map.get("id"));
        BigDecimal price = new BigDecimal(map.get("price"));
        BigDecimal number = new BigDecimal(map.get("number"));
        String reviewId = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance();
        String sbTime = calendar.get(Calendar.YEAR) + "";
        sbVo.setPrices(price.multiply(number).toString());
        sbVo.setNumber(map.get("outNumber"));
        sbVo.setId(map.get("id"));
        sbVo.setSbYear(sbTime);
        sbVo.setConType(map.get("out_type"));
        sbVo.setOperator(map.get("operator"));
        sbVo.setReview_id(reviewId);
        sbVo.setLibraryName(map.get("role4"));
        sbVo.setStatus_z("4");
        sbVo.setStatus("0");
        sbDao.updateSbReview(sbVo);

        //插入审核数据
        List<ReviewTable> list = new ArrayList<>();
        ReviewTable reviewTable = new ReviewTable();
        reviewTable.setReview_id(reviewId);
        reviewTable.setProject_name("归还审核");
        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role1"));
        reviewTable.setBefore_re(null);
        reviewTable.setAfter_re(map.get("role2"));
        reviewTable.setStatus("0");
        reviewTable.setRole("专业负责人");
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role2"));
        reviewTable.setBefore_re(map.get("role1"));
        reviewTable.setAfter_re(map.get("role3"));
        reviewTable.setStatus(null);
        reviewTable.setRole("二级学院管理员");
        sbDao.AddSbReview(reviewTable);

        reviewTable.setId(UUID.randomUUID().toString());
        reviewTable.setRe_pe_id(map.get("role3"));
        reviewTable.setBefore_re(map.get("role2"));
        reviewTable.setAfter_re(null);
        reviewTable.setStatus(null);
        reviewTable.setRole("二级学院教学副院长");
        list.add(reviewTable);
        sbDao.AddSbReview(reviewTable);
        return 0;
    }

    /**
     * 借用审核库管员出库
     * @param sbVos
     * @return
     */
    @Override
    public List<SbVo> out(List<SbVo> sbVos) {
        List<SbVo> sbVoList = new ArrayList<>();
        for (SbVo storageSbVo : sbVos) {
            ConOut conOut = new ConOut();
            conOut.setBorrow_review_id(storageSbVo.getReview_id());
            conOut.setStatus_z("3");//出库表 出库完成

            SbVo storageCon = sbDao.getSbById(storageSbVo.getReview_id());//获得耗材借用表数据
            SbVo sbVo = sbDao.getNumber(storageCon);//库存
            int number = Integer.parseInt(sbVo.getNumber());//获取库存数量
            int outNumber = (int) Double.parseDouble(storageCon.getNumber());//出库的数量
            int count = number - outNumber;//出库后库存
            if (count < 0) {
                sbVoList.add(sbVo);
                return sbVoList;
            }

            BigDecimal price = new BigDecimal(storageCon.getPrice());
            BigDecimal num = new BigDecimal(count);
            BigDecimal prices = price.multiply(num);//price*num 出库后总价格

            storageCon.setNumber(String.valueOf(count));
            storageCon.setPrices(String.valueOf(prices));
            storageCon.setStatus_z("7");//最终审核状态 已出库

            ReviewParam reviewParam = new ReviewParam();
            reviewParam.setReview_id(storageSbVo.getReview_id());
            reviewParam.setRole("库管员");
            System.err.println(reviewParam);
            reviewParam.setProject_name("外借审核");
            ReviewParam revId = sbDao.getRevId(reviewParam);//获取审核流程表id
            revId.setStatus("1");//库管员审核状态，通过

            int resultMe = reviewDao.reviewUpdateMe(revId);//修改自己的审核状态，审核通过
            System.err.println("count==>" + count);

            storageCon.setCon_type(storageCon.getConType());

            int update = sbDao.update(storageCon);//修改库存表数量和总价格

            reviewDao.updateOutStatus(conOut);//出库表出库完成

            storageCon.setReview_id(storageSbVo.getReview_id());
            int i = sbDao.updateReview(storageCon);//修改最终审核状态
        }
        return sbVoList;
    }

    /**
     * 入库
     * @param sbVos
     * @return
     */
    @Override
    public Boolean storage(List<SbVo> sbVos) {
        List<SbVo> sbVoList = new ArrayList<>();

        for (SbVo storageSbVo : sbVos) {
            SbVo storageCon = sbDao.getSbById(storageSbVo.getReview_id());//借用表数据
            String inNumber = storageCon.getNumber();//要入库的数量 借用的数量

            SbVo sbVo = sbDao.getNumber(storageCon);//库存
            storageCon.setProject_name(storageSbVo.getProject_name());

            BigDecimal number = null;
            if (sbVo != null) {
                number = new BigDecimal(sbVo.getNumber());
            } else {
                number = new BigDecimal("0");
            }

            int numa = Integer.parseInt(String.valueOf(number));//库存
            if (sbVo == null) {//库存表中没有查到
                System.err.println("sbVo == null" + sbVo);
                //添加库存表数据
                String conId = UUID.randomUUID().toString();//库存表id
                storageCon.setId(conId);
                storageCon.setStatus_b("0");//报废状态 未报废
                storageCon.setStatus("1");//借用状态 可借用

                BigDecimal price1 = new BigDecimal(storageCon.getPrice());
                BigDecimal num1 = new BigDecimal(inNumber);
                BigDecimal prices1 = price1.multiply(num1);
                storageCon.setPrices(String.valueOf(prices1));
                storageCon.setStandards(storageCon.getStandards());

                sbDao.addCon(storageCon);//添加库存表数据
            } else {
                System.err.println("sbVo != null" +sbVo);
                //修改库存表数据
                String num = "0";
                if (sbVo != null) {
                    num = sbVo.getNumber();
                }
                BigDecimal conNumber1 = new BigDecimal(num);//库存数
                BigDecimal inNumber1 = new BigDecimal(inNumber);
                BigDecimal count1 = conNumber1.add(inNumber1);//入库后总数量
                String count = String.valueOf(count1);

                BigDecimal price = new BigDecimal(storageCon.getPrice());
                BigDecimal num1 = new BigDecimal(count);
                BigDecimal prices = price.multiply(num1);//入库后总价格

                storageCon.setNumber(count);
                storageCon.setPrices(String.valueOf(prices));
                storageCon.setCon_type(storageCon.getConType());
                sbDao.update(storageCon);//修改库存表数量和总价格

            }


            ReviewParam reviewParam = new ReviewParam();
            reviewParam.setReview_id(storageSbVo.getReview_id());
            reviewParam.setRole("库管员");
            reviewParam.setProject_name("归还审核");
            ReviewParam revId = sbDao.getRevId(reviewParam);//获取审核流程表id
            revId.setStatus("1");//库管员审核状态，通过

            int resultMe = reviewDao.reviewUpdateMe(revId);//修改自己的审核状态，审核通过
            storageCon.setReview_id(storageSbVo.getReview_id());
            storageCon.setStatus_z("8");
            int i = sbDao.updateReview(storageCon);//修改最终审核状态
        }
        return true;
    }
}
