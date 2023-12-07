package com.haocai.module.purchase.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.purchase.service.purchaseService;
import com.haocai.module.purchase.vo.purchase;
import com.haocai.module.purchase.dao.purchaseMapper;
import com.haocai.module.system.vo.User;
import com.haocai.module.system.vo.UserMajor;
import com.haocai.utils.ValidateUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.jar.JarException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class purchaseServiceImp implements purchaseService {
@Autowired
    private purchaseMapper purchaseMapper;
    @Override
    /* 采购表单信息 */
    public List<purchase> purchaselist(String consumablesid,String department) {
        List<purchase> purchaseList = purchaseMapper.purchaselist(consumablesid,department);
        for (purchase purchase : purchaseList) {
            String regex="([0-9]\\d*\\.?\\d+)|(0\\.\\d*[0-9])|(\\d+)";
            Pattern pattern= Pattern.compile(regex);
            Matcher matcher=pattern.matcher(purchase.getNumberMark());
            while(matcher.find()) {
                Float group = Float.valueOf(matcher.group());
                purchase.setStudentsavg(group);
            }
        }

        String str=purchaseList.get(0).getSemester();
        String [] part=str.split(",");
        String [] clone =new String[part.length];
        String end="";
        for(int i=0;i<part.length;i++){
            if(part[i].equals("1")){
                clone[i]="第一学期,";
               end=end+clone[i];
            }else if(part[i].equals("2")){
                clone[i]="第二学期,";
                end=end+clone[i];
            }else if(part[i].equals("3")){
                clone[i]="第三学期,";
                end=end+clone[i];
            }else if(part[i].equals("4")){
                clone[i]="第四学期,";
                end=end+clone[i];
            }else if(part[i].equals("5")){
                clone[i]="第五学期,";
                end=end+clone[i];
            }else if(part[i].equals("6")){
                clone[i]="第六学期,";
                end=end+clone[i];
            }
        }
        end=end.substring(0,end.length()-1);
        purchaseList.get(0).setSemester(end);
        return purchaseList;
    }
//    采购修改列表
    public List<purchase> changepurchaselist(String consumablesid,Integer id) {
        List<purchase> changepurchaseList = purchaseMapper.changepurchaselist(consumablesid,id);
        String str=changepurchaseList.get(0).getSemester();
        String [] part=str.split(",");
        String [] clone =new String[part.length];
        String end="";
        for(int i=0;i<part.length;i++){
            if(part[i].equals("1")){
                clone[i]="第一学期,";
                end=end+clone[i];
            }else if(part[i].equals("2")){
                clone[i]="第二学期,";
                end=end+clone[i];
            }else if(part[i].equals("3")){
                clone[i]="第三学期,";
                end=end+clone[i];
            }else if(part[i].equals("4")){
                clone[i]="第四学期,";
                end=end+clone[i];
            }else if(part[i].equals("5")){
                clone[i]="第五学期,";
                end=end+clone[i];
            }else if(part[i].equals("6")){
                clone[i]="第六学期,";
                end=end+clone[i];
            }
        }
        end=end.substring(0,end.length()-1);
        changepurchaseList.get(0).setSemester(end);
        return changepurchaseList;
    }
//    采购修改
    public int updatepurchase(purchase purchase){
        return purchaseMapper.updatepurchase(purchase);
    }
//    采购表添加
    public  int purchasecheck(purchase purchase,User user){
        purchase.setOperatorname(user.getName());
        purchase.setOperator(user.getAccount());
        Date date = new Date();
        System.out.println(date);
//        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy/MM-dd/编号:HH-mm-ss");
//        String format = dateTimeFormatter.format(date);
//        purchase.setMark(format);
        return purchaseMapper.purchasecheck(purchase);
    }
//    查询采购表
    public List<purchase> sel_all(String purchaseid){
        return purchaseMapper.sel_all(purchaseid);
    }
//    删除采购表数据
    public int deletepurchase(Integer id){
        return purchaseMapper.deletepurchase(id);
    }

    public int deletepC(String id){
        return purchaseMapper.deletepC(id);
    }

//    采购表单提交审核
    public int sub_purchase(String Allid,User user,String poinion,String auditoid){
        String status="";
        String role=user.getRoleName();
        String account= user.getAccount();
        String operatorname=user.getName();
        String [] allid=Allid.split(",");
        String department=user.getDeptName();
        String [] a=auditoid.split(",");
        //查询审核人姓名
        String one ="";
        one=purchaseMapper.auditor(a[0]);
        String two=a[1];
        two=purchaseMapper.auditor(a[1]);
        String three=a[2];
        three=purchaseMapper.auditor(a[2]);
        String four=a[3];
         four=purchaseMapper.auditor(a[3]);

//        poinion=ValidateUtil.isNotEmpty(poinion)?poinion:"审核通过";



        if(role.indexOf("专业负责人")!=-1){
            for(int i=0;i<allid.length;i++){
                String purchaseid = UUID.randomUUID().toString().replaceAll("-","");
                String id1 = UUID.randomUUID().toString().replaceAll("-","");
                String id2 = UUID.randomUUID().toString().replaceAll("-","");
                String id3 = UUID.randomUUID().toString().replaceAll("-","");
                String id4 = UUID.randomUUID().toString().replaceAll("-","");
                int part=Integer.parseInt(allid[i]);
                purchaseMapper.changepurchaseid(purchaseid,part);
                purchaseMapper.sub_purchase(part,"2",department);
                purchaseMapper.shenheliucheng(id1,id2,id3,id4,purchaseid,"1",account,department,operatorname,one,two,three,four);
                purchaseMapper.updatestatu("1",purchaseid);
            }
            //审核表 插入四条数据
//            purchaseMapper.changeliucheng(purchaseid,"1",role);
           //提交 改成二级学院管理员

        }
        return 1;
    }



//    二级学院管理员查询
    public List<purchase> sel_teacher(String department,purchase purchase,String fbegintime,String fovertime,Integer limit,Integer page,String major){
        if(purchase.getStatus()!=null){
            if(purchase.getStatus().equals("审核中")){
                purchase.setStatus("2");
            }
            if(purchase.getStatus().equals("未审核")){
                purchase.setStatus("0");
            }
            if(purchase.getStatus().equals("审核通过")){
                purchase.setStatus("1");
            }
            if(purchase.getStatus().equals("退回")){
                purchase.setStatus("3");
            }
        }
        String begintime="";
        String overtime="";
        int originate=(page-1)*limit;
        int end=(page*limit);
        if(ValidateUtil.isNotEmpty(fovertime)&&ValidateUtil.isNotEmpty(fbegintime)){
           begintime=fbegintime+" 00:00:00";
           overtime=fovertime+" 23:59:59";
        }
        return purchaseMapper.sel_teacher(department,purchase,begintime,overtime,originate,end,major);
    }


    public List<purchase> another_sel(User user,purchase purchase,Integer limit,String fbegintime,String fovertime,Integer page,String status,String department){
        String role=user.getRoleName();
        String account=user.getAccount();
        String begintime="";
        String overtime="";
        if(ValidateUtil.isNotEmpty(fovertime)&&ValidateUtil.isNotEmpty(fbegintime)){
            begintime=fbegintime+" 00:00:00";
            overtime=fovertime+" 23:59:59";
        }
        int originate=(page-1)*limit;
        int end=(page*limit);
        String operator=user.getName();
        purchase.setStatus(status);
        purchase.setOperatorname(user.getName());
        return purchaseMapper.another_sel(department,purchase,begintime,overtime,originate,end,purchase.getMajor());
    }
    public int another_selNumber(User user,purchase purchase,Integer limit,String fbegintime,String fovertime,Integer page,String status,String department){
        String role=user.getRoleName();
        String account=user.getAccount();
        String begintime="";
        String overtime="";
        if(ValidateUtil.isNotEmpty(fovertime)&&ValidateUtil.isNotEmpty(fbegintime)){
            begintime=fbegintime+" 00:00:00";
            overtime=fovertime+" 23:59:59";
        }
        int originate=(page-1)*limit;
        int end=(page*limit);
        String operator=user.getName();
        purchase.setStatus(status);
        purchase.setOperatorname(user.getName());
        return purchaseMapper.another_selNumber(department,purchase,begintime,overtime,purchase.getMajor());
    }

    public List<purchase> another_selprice(User user,purchase purchase,String fbegintime,String fovertime,String status,String department,String major){
        String begintime="";
        String overtime="";
        if(ValidateUtil.isNotEmpty(fovertime)&&ValidateUtil.isNotEmpty(fbegintime)){
            begintime=fbegintime+" 00:00:00";
            overtime=fovertime+" 23:59:59";
        }
        purchase.setStatus(status);
        purchase.setOperatorname(user.getName());
        return purchaseMapper.another_selprice(department,purchase,begintime,overtime,major);
    }


    public List<purchase> sel_teacherprice(String department,purchase purchase,String fbegintime,String fovertime,String major){
        if(purchase.getStatus()!=null){
            if(purchase.getStatus().equals("审核中")){
                purchase.setStatus("2");
            }
            if(purchase.getStatus().equals("未审核")){
                purchase.setStatus("0");
            }
            if(purchase.getStatus().equals("审核通过")){
                purchase.setStatus("1");
            }
            if(purchase.getStatus().equals("退回")){
                purchase.setStatus("3");
            }
        }
        String begintime="";
        String overtime="";
        if(ValidateUtil.isNotEmpty(fovertime)&&ValidateUtil.isNotEmpty(fbegintime)){
            begintime=fbegintime+" 00:00:00";
            overtime=fovertime+" 23:59:59";
        }
        return purchaseMapper.sel_teacherprice(department,purchase,begintime,overtime,major);
    }
//    审核页面
    public List<Map<String,Object>> shenhepage(String purchaseid){
        return purchaseMapper.selstudent(purchaseid);
    }
//    查询所有
    public List<purchase> sel_purchaseall(Integer limit,Integer page){
        int originate=(page-1)*limit;
        int end=(page*limit)-1;
        return purchaseMapper.sel_purchaseall(originate,end);
    }
//    查条数
    public int sel_purchasenumber(){
        return purchaseMapper.sel_purchaseallnumber();
    }
//    审核退回
    public int shenhetuihui(String opinion,String purchaseid,User user){
        String operatorname=user.getName();
        String account=user.getAccount();
        String role=user.getRoleName();
        opinion=ValidateUtil.isNotEmpty(opinion)?opinion:"审核退回";

        if(role.indexOf("二级学院管理员")!=-1){
            //审核表 退回'3'
             purchaseMapper.TuiHuiR(purchaseid,operatorname);

            //采购表 设置 stauts '3'
            purchaseMapper.TuiHuiP(purchaseid);
            //意见 添加
             purchaseMapper.changeopin(opinion,purchaseid);
             purchaseMapper.Ropinionone(opinion,operatorname,purchaseid);
        }

        if(role.indexOf("二级学院教学院长")!=-1){
            //审核表 退回'3'
            purchaseMapper.TuiHuiR(purchaseid,operatorname);
            //采购表 设置 stauts '3'
            purchaseMapper.TuiHuiP(purchaseid);
            //意见 添加
            purchaseMapper.changeopin(opinion,purchaseid);
            purchaseMapper.Ropinion(opinion,operatorname);
        }

        if(role.indexOf("教务处管理员")!=-1){
            //审核表 退回'3'
            purchaseMapper.TuiHuiR(purchaseid,operatorname);
            //采购表 设置 stauts '3'
            purchaseMapper.TuiHuiP(purchaseid);
            //意见 添加
            purchaseMapper.changeopin(opinion,purchaseid);
            purchaseMapper.Ropinion(opinion,operatorname);
        }
            return 1;
    }
    public int pShenHeBack(String opinion,String pShenId,User user){
        String operatorname=user.getName();
        String role=user.getRoleName();
        String[] pId = pShenId.split(",");
        opinion=ValidateUtil.isNotEmpty(opinion)?opinion:"审核退回";
        if(role.indexOf("二级学院管理员")!=-1){
            for (String purchaseid : pId) {
                //审核表 退回'3'
                purchaseMapper.TuiHuiR(purchaseid,operatorname);
                //采购表 设置 stauts '3'
                purchaseMapper.TuiHuiP(purchaseid);
                //意见 添加
                purchaseMapper.changeopin(opinion,purchaseid);
                purchaseMapper.Ropinion(opinion,operatorname);
            }

        }

        if(role.indexOf("二级学院教学院长")!=-1){
            for (String purchaseid : pId) {
                //审核表 退回'3'
                purchaseMapper.TuiHuiR(purchaseid,operatorname);
                //采购表 设置 stauts '3'
                purchaseMapper.TuiHuiP(purchaseid);
                //意见 添加
                purchaseMapper.changeopin(opinion,purchaseid);
                purchaseMapper.Ropinion(opinion,operatorname);
            }

        }

        if(role.indexOf("教务处管理员")!=-1){
            for (String purchaseid : pId) {
                //审核表 退回'3'
                purchaseMapper.TuiHuiR(purchaseid,operatorname);
                //采购表 设置 stauts '3'
                purchaseMapper.TuiHuiP(purchaseid);
                //意见 添加
                purchaseMapper.changeopin(opinion,purchaseid);
                purchaseMapper.Ropinion(opinion,operatorname);
            }

        }

//        if(role.indexOf("采购中心")!=-1){
//            //审核表 退回'3'
//            purchaseMapper.TuiHuiR(purchaseid);
//            //采购表 设置 stauts '3'
//            purchaseMapper.TuiHuiP(purchaseid);
//            //意见 添加
//            purchaseMapper.changeopin(opinion,purchaseid);
//            purchaseMapper.Ropinion(opinion,operatorname);
//        }

        return 1;
    }


    //    查询材料名称 类型
//    public String purchasename(String department){
//        return purchaseMapper.purchasename(department);
//    }
//    其他人查询


//    查list表总数
    public int SelListNumber(){
        return purchaseMapper.sel_listnumber();
    }
//    获取角色
    public String selRname(int rid){
        return purchaseMapper.selRname(rid);
    }
//    查list表
   public List<purchase> purchasename(String department,String major){
        return purchaseMapper.purchasename(department,major);
   }

    @Override
    public String major(String name) {
        return purchaseMapper.major(name);
    }

    public List<String> majorShen(String department) {
        return purchaseMapper.majorShen(department);
    }

    public int sel_teacherNumber(String department,purchase purchase,String fbegintime,String fovertime,String major){
       String begintime="";
       String overtime="";
       if(ValidateUtil.isNotEmpty(fovertime)&&ValidateUtil.isNotEmpty(fbegintime)){
           begintime=fbegintime+" 00:00:00";
           overtime=fovertime+" 23:59:59";
       }
        return purchaseMapper.sel_teacherNumber(department,purchase,begintime,overtime,major);
   }
//   查姓名审核状态
    public List<Map<String,String>> namestatus(User user){
        List<Map<String,String>> maps = new ArrayList<>();
        HashMap<String,String> hashMap1 = new HashMap<>();
        HashMap<String,String> hashMap2 = new HashMap<>();
        String position=user.getRoleName();

        if(position.indexOf("二级学院教学院长")!=-1){
            hashMap1.put("name","二级学院管理员审核通过");
            hashMap1.put("status","0");
            maps.add(0,hashMap1);
            hashMap2.put("name","二级学院院长审核退回");
            hashMap2.put("status","3");
            maps.add(1,hashMap2);
        }
        if(position.equals("教务处管理员")){
            hashMap1.put("name","二级学院院长审核通过");
            hashMap1.put("status","0");
            maps.add(0,hashMap1);
            hashMap2.put("name","教务处管理员审核退回");
            hashMap2.put("status","3");
            maps.add(1,hashMap2);
        }
        if(position.equals("采购中心")){
            hashMap1.put("name","教务处管理员审核通过");
            hashMap1.put("status","0");
            maps.add(0,hashMap1);
        }
        return maps;
    }
//    审核意见
    public List<purchase> shenheyijian(String purchaseid){
        return purchaseMapper.shenhepage(purchaseid);
    }
    //    审核
    public int shenhe(String purchaseid,User user,String opinion){
        String role=user.getRoleName();
        String statu="";
        String account= user.getAccount();
        String operatorname=user.getName();
        String department=user.getDeptName();
        opinion=ValidateUtil.isNotEmpty(opinion)?opinion:"审核通过";
        if(role.indexOf("二级学院管理员")!=-1){
            //审核表 设置 stauts '1'
            int i = purchaseMapper.changeliucheng(purchaseid, "1", "二级学院管理员");
            //审核表 设置 stauts '4'
            purchaseMapper.changeliucheng(purchaseid,"0","二级学院教学院长");
            // 采购表 设置 流程staut '2'
            purchaseMapper.updatestatu("2",purchaseid);
            //意见 添加
            purchaseMapper.addopinion(purchaseid,opinion,account,operatorname);
        }

        if(role.indexOf("二级学院教学院长")!=-1){
            purchaseMapper.changeliucheng(purchaseid,"1","二级学院教学院长");
            purchaseMapper.changeliucheng(purchaseid,"0","教务处管理员");
            purchaseMapper.updatestatu("3",purchaseid);
            purchaseMapper.addopinion(purchaseid,opinion,account,operatorname);
        }
        if(role.indexOf("教务处管理员")!=-1){
            purchaseMapper.changeliucheng(purchaseid,"5","教务处管理员");
            purchaseMapper.changeliucheng(purchaseid,"5","采购中心");
            purchaseMapper.changeliucheng(purchaseid,"5","二级学院管理员");
            purchaseMapper.changeliucheng(purchaseid,"5","二级学院管理员");
            purchaseMapper.updatestatu("5",purchaseid);
            purchaseMapper.updatestatus(purchaseid,"1");
            purchaseMapper.addopinion(purchaseid,opinion,account,operatorname);
        }
        return 1;
    }

    @Override
    public int shenAgain(String purchaseid, User user) {
        String role=user.getRoleName();
        String statu="";
        String account= user.getAccount();
        String operatorname=user.getName();
        String department=user.getDeptName();
        if(role.indexOf("专业负责人")!=-1){
            if (purchaseid.split(",").length>0){
                String[] pid = purchaseid.split(",");
                for (String purchaseid1 : pid) {
                    purchaseMapper.changeliucheng(purchaseid1,"0","二级学院管理员");
                    purchaseMapper.changeliucheng(purchaseid1,"4","二级学院教学院长");
                    purchaseMapper.changeliucheng(purchaseid1,"4","教务处管理员");
                    purchaseMapper.changeliucheng(purchaseid1,"4","采购中心");
                    purchaseMapper.updatestatu("1",purchaseid1);
                    purchaseMapper.updatestatus(purchaseid1,"2");
                    purchaseMapper.Pclearopinion(purchaseid1);
                    purchaseMapper.Rclearopinion(purchaseid1);
                }
            }else {
                purchaseMapper.changeliucheng(purchaseid,"0","二级学院管理员");
                purchaseMapper.changeliucheng(purchaseid,"4","二级学院教学院长");
                purchaseMapper.changeliucheng(purchaseid,"4","教务处管理员");
                purchaseMapper.changeliucheng(purchaseid,"4","采购中心");
                purchaseMapper.updatestatu("1",purchaseid);
                purchaseMapper.updatestatus(purchaseid,"2");
                purchaseMapper.Pclearopinion(purchaseid);
                purchaseMapper.Rclearopinion(purchaseid);
            }
        }
        return 1;
    }

    @Override
    public int updateStudentNumber(String purchaseid, Float studentNumber) {
        return purchaseMapper.updateStudentNumber(purchaseid,studentNumber);
    }

    @Override
    public int deleteAll(String purchaseid) {
        return purchaseMapper.deleteAll(purchaseid);
    }
    @Override
    public int deleteAllP(String purchaseid) {
        return purchaseMapper.deleteAll(purchaseid);
    }

    public int pShenHePass(String pShenId,User user,String opinion){
        String role=user.getRoleName();
        String account= user.getAccount();
        String operatorname=user.getName();
        String department=user.getDeptName();
        opinion=ValidateUtil.isNotEmpty(opinion)?opinion:"审核通过";
        String[] pId = pShenId.split(",");

        if(role.indexOf("二级学院管理员")!=-1){
            for (String purchaseid : pId) {
                //审核表 设置 stauts '1'
                purchaseMapper.changeliucheng(purchaseid,"1","二级学院管理员");
                //审核表 设置 stauts '4'
                purchaseMapper.changeliucheng(purchaseid,"0","二级学院教学院长");
                // 采购表 设置 流程staut '2'
                purchaseMapper.updatestatu("2",purchaseid);
                purchaseMapper.addopinion(purchaseid,opinion,account,operatorname);
            }


//            purchaseMapper.updatestatu(statu,purchaseid);
            //意见 添加

        }

        if(role.indexOf("二级学院教学院长")!=-1){
            for (String purchaseid : pId) {
                purchaseMapper.changeliucheng(purchaseid,"1","二级学院教学院长");
                purchaseMapper.changeliucheng(purchaseid,"0","教务处管理员");
                purchaseMapper.updatestatu("3",purchaseid);
                purchaseMapper.addopinion(purchaseid,opinion,account,operatorname);
            }
        }
        if(role.indexOf("教务处管理员")!=-1 || role.indexOf("超级管理员")!=-1){
            for (String purchaseid : pId) {
                purchaseMapper.changeliucheng(purchaseid,"5","教务处管理员");
                purchaseMapper.changeliucheng(purchaseid,"5","采购中心");
                purchaseMapper.changeliucheng(purchaseid,"5","二级学院教学院长");
                purchaseMapper.changeliucheng(purchaseid,"5","二级学院管理员");
                purchaseMapper.updatestatu("5",purchaseid);
                purchaseMapper.updatestatus(purchaseid,"1");
                purchaseMapper.addopinion(purchaseid,opinion,account,operatorname);
                purchaseMapper.changeopin(opinion,purchaseid);
            }

        }
        return 1;
    }


    public static String removeZeros(String num){
        if (num.indexOf(".") > 0) {
            // 去掉多余的0
            num = num.replaceAll("0+?$", "");
            // 如果最后一位是. 则去掉
            num = num.replaceAll("[.]$", "");
        }
        return num;
    }
//    excel导入
    public HashMap<String, Object> daoru(MultipartFile file, User user)   {
        HashMap<String,Object> hashMap=new HashMap<>();
        List<String> list=new ArrayList<>();
           HSSFWorkbook xssfWorkbook=null;
        purchase purchase = new purchase();
        purchase.setOperator(user.getAccount());
        try {
            InputStream inputStream = file.getInputStream();
            xssfWorkbook=new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet=xssfWorkbook.getSheetAt(0);
        HSSFRow row;
        int rowNum=sheet.getLastRowNum();
        int f=1;
        for(int i=1;i<=rowNum;i++){
            row=sheet.getRow(i);
            if(ValidateUtil.isEmpty(row.getCell(11).toString())||Float.valueOf(row.getCell(11).toString())==0){
                continue;
            }
            String s= String.valueOf(row.getCell(0));
            String consumablesid=removeZeros(s);
            String name=String.valueOf(row.getCell(1));
            String attribute= String.valueOf(row.getCell(2));
            String type= String.valueOf(row.getCell(3));
            String standards=String.valueOf(row.getCell(4));
            String unit=String.valueOf(row.getCell(5));
            Float price= Float.valueOf(row.getCell(6).toString());
            String department=String.valueOf(row.getCell(7));
            String major=String.valueOf(row.getCell(8));
            String training=String.valueOf(row.getCell(9));
            String studentsavg= (String.valueOf(row.getCell(10)));
            purchase.setConsumablesid(consumablesid);
            purchase.setName(name);
            purchase.setAttribute(String.valueOf(attribute));
            purchase.setType(String.valueOf(type));
            purchase.setStandards(standards);
            purchase.setStudentsavg(Float.valueOf(studentsavg));
            purchase.setUnit(unit);
            purchase.setPrice(price);
            purchase.setDepartment(department);
            purchase.setTraining(training);
            purchase.setMark(major);
//            Float kcnumber=purchaseMapper.selnumber(purchase.getConsumablesid(),purchase.getPrice());
//            Float prnumber=purchaseMapper.selpurnumber(purchase.getConsumablesid(),purchase.getOperator());
            purchase.setStudentnumber(Float.valueOf(row.getCell(11).toString()));
            purchase.setNumber(purchase.getStudentsavg()*purchase.getStudentnumber());
            purchase.setOperator(user.getAccount());
            purchase.setOperatorname(user.getName());
            String ss = purchaseMapper.ListDep(consumablesid,user.getName());
            if (ss==null){
               purchaseMapper.purchaselistin(purchase);

            }else {
                String msg="库存充足或已在采购流程中,无需采购";
                hashMap.put(consumablesid, msg);
            }

            //????????
//            if(ValidateUtil.isNotEmpty(kcnumber) || ValidateUtil.isNotEmpty(prnumber)){
//                if(ValidateUtil.isEmpty(kcnumber)){
//                    kcnumber= Float.valueOf(0);
//                }
//                if(ValidateUtil.isEmpty(prnumber)){
//                    prnumber= Float.valueOf(0);
//                }
//                if(purchase.getNumber()<=(kcnumber+prnumber)){
//                    System.out.println("采购失败");
//                    String mark="库存充足或已在采购流程中,无需采购";
//                    String [] m={consumablesid,name,attribute,type,standards,studentsavg,unit, String.valueOf(price),department,mark};
//                    for(int l=0;l<m.length;l++){
//                        list.add(m[l]);
//                    }
//                    hashMap.put(consumablesid, list);
//                }
//            }
//            if(ValidateUtil.isEmpty(kcnumber) && ValidateUtil.isEmpty(prnumber)){
//                purchaseMapper.purchaselistin(purchase);
//            }
        }
        return hashMap;
    }
//    模板
    public List<purchase> moban(String department,String major){
       return purchaseMapper.excel(department, major);
    }
    public List<purchase> mobanDepartment(String department){
        return purchaseMapper.excelDepartment(department);
    }
//    查询采购的所有信息
    public HashMap<String,Object> selpurchaseall(String department,Integer limit,Integer page,String timeLimit){
        HashMap<String,Object> map = new HashMap<String,Object>();
        int originate=(page-1)*limit;
        int end=(page*limit);
        String begintime="";
        String overtime="";
        if(ValidateUtil.isNotEmpty(timeLimit)){
            begintime=timeLimit.split(" - ")[0] + " 00:00:00";
            overtime=timeLimit.split(" - ")[1] + " 23:59:59";
        }
        // 获取所有采购记录
        List<purchase> selpurchaseall = purchaseMapper.selpurchaseall(department, originate, end, begintime, overtime);
        // 获取采购记录总条数
        int selpurchaseallCount = purchaseMapper.selpurchaseallCount(department, begintime, overtime);
        map.put("data",selpurchaseall);
        map.put("count",selpurchaseallCount);
        return map;
    }
//    教务处查询
    public List<purchase> jwcsel(String department){
        return purchaseMapper.jwcsel(department);
    }
//    采购中心查询
    public List<purchase> cgzxsel(){
       return purchaseMapper.cgzxsel();
    }

    @Override
    public String judgmentStuts(int id) {
        return purchaseMapper.judgmentStuts(id);
    }

    @Override
    public String judgmentshenhe(String purchaseid,String name) {
        return purchaseMapper.judgmentshenhe(purchaseid,name);
    }

    @Override
    public List<purchase> pShenAll(String purchaseid) {
       return purchaseMapper.pShenAll(purchaseid);
    }

    @Override
    public String ListDep(@Param("consumablesid")String consumablesid, @Param("operatorname")String operatorname) {
        return purchaseMapper.ListDep(consumablesid, operatorname);
    }
}
