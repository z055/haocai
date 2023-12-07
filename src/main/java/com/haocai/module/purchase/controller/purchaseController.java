package com.haocai.module.purchase.controller;

import cn.hutool.db.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haocai.module.inventory.vo.InventoryVo;
import com.haocai.module.purchase.service.purchaseService;
import com.haocai.module.purchase.vo.purchase;
import com.haocai.module.system.service.UserService;
import com.haocai.module.system.service.impl.UserServiceImpl;
import com.haocai.module.system.vo.User;
import com.haocai.utils.JsonTemplate;
import com.haocai.utils.ValidateUtil;
import org.junit.runners.model.MultipleFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import scala.Int;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/purchase")

public class purchaseController {
    @Autowired
    private purchaseService purchaseService;

//    采购表单
    @RequestMapping("/purchaselist")
    @ResponseBody
    public String purchaseList(@RequestParam("consumablesid")String consumablesid,@RequestParam("department") String department)throws JsonProcessingException {
        System.err.println(consumablesid+"-----");
        return new ObjectMapper().writeValueAsString(purchaseService.purchaselist(consumablesid,department));
    }

    //采购 全部审核信息



    //    采购修改表单
    @RequestMapping("/changepurchaselist")
    @ResponseBody
    public String changepurchaseList(@RequestParam("consumablesid")String consumablesid,@RequestParam("id")Integer id)throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(purchaseService.changepurchaselist(consumablesid,id));
    }
//    采购表单修改
    @RequestMapping("/updatepurchase")
    @ResponseBody
    public String updatechase(@RequestBody purchase purchase)throws JsonProcessingException{
        int changepurchase =purchaseService.updatepurchase(purchase);
        String l="";
        if(changepurchase==1){
            l="修改成功";
        }else{
            l="修改失败";
        }
        return l;
    }
//    采购
    @RequestMapping("/purchasecheck")
    @ResponseBody
    public int purchasecheck(@RequestBody purchase purchase,HttpServletRequest httpServletRequest)throws JsonProcessingException{
        User user=(User) httpServletRequest.getSession().getAttribute("user");
        int t=purchaseService.purchasecheck(purchase,user);
        return t;
    }
//    查询采购表所有
    @RequestMapping("/selAll")
    @ResponseBody
    public String sel_all(@RequestParam("purchaseid")String purchaseid)throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(purchaseService.sel_all(purchaseid));
    }
//    删除采购表数据
    @RequestMapping("/deletepurchase")
    @ResponseBody
    public int deletepurchase(Integer id) throws JsonProcessingException{
        int deletepurchase=purchaseService.deletepurchase(id);
        return deletepurchase;
    }

    //审核删除
    @ResponseBody
    @RequestMapping("/delAll")
    public int delAll(Integer id,String purchaseid){
        int deletepurchase=purchaseService.deletepurchase(id);
        int i = purchaseService.deleteAll(purchaseid);
        return deletepurchase;
    }

//    采购提交审核
    @RequestMapping("/subPurchase")
    @ResponseBody
    public int sub_purchase(@RequestParam("auditoid")String auditoid,@RequestParam("Allid")String Allid, @RequestParam(value = "poinion" ,required = false)String poinion, HttpServletRequest httpServletRequest)throws  JsonProcessingException{
        List<purchase> b=new ArrayList<>();
        User user= (User) httpServletRequest.getSession().getAttribute("user");
        int m=purchaseService.sub_purchase(Allid,user,poinion,auditoid);
        return m;
    }
//    采购管理 查询
    @RequestMapping("/selTeacher")
    @ResponseBody
    public String sel_teacher(@RequestParam("department") String department, purchase purchase, @RequestParam("time")String time, @RequestParam("limit")Integer limit, @RequestParam("page")Integer page,@RequestParam("major") String major, HttpServletRequest httpServletRequest)throws JsonProcessingException{
        String [] alltime;
        String fbegintime="";
        String fovertime="";
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        purchase.setOperator(user.getAccount());
        if(ValidateUtil.isNotEmpty(time)){
             alltime=time.split(" - ");
         fbegintime=alltime[0];
          fovertime=alltime[1];
        }
        List<purchase> sel_teacher=new ArrayList<>();
            sel_teacher =purchaseService.sel_teacher(department,purchase,fbegintime,fovertime,limit,page,major);
        for (com.haocai.module.purchase.vo.purchase purchase1 : sel_teacher) {
//            purchase1.setNumber(purchase1.getStudentsavg()*purchase1.getStudentnumber());
//            BigDecimal price = new BigDecimal(purchase1.getPrice());
//            BigDecimal num = new BigDecimal(purchase1.getStudentnumber());
//            BigDecimal prices ;
//            prices=price.multiply(num);

            BigDecimal price1 = new BigDecimal(String.valueOf(purchase1.getPrice()));
            BigDecimal studentnumber = new BigDecimal(String.valueOf(purchase1.getStudentnumber()));
            BigDecimal studentsavg = new BigDecimal(String.valueOf(purchase1.getStudentsavg()));
            BigDecimal all =BigDecimal.ZERO;
            all=price1.multiply(studentnumber).multiply(studentsavg);
            purchase1.setAllPrices(String.valueOf(all));
                String num = String.valueOf(studentnumber.multiply(studentsavg));
                if (num.indexOf(".") > 0) {//判断是否有小数点
                    num = num.replaceAll("0+?$", "");//去掉多余的0
                    num = num.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
            purchase1.setNumber1(num);
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("sel_teacher",sel_teacher);
        JsonTemplate jsonTemplate=new JsonTemplate("查询成功",sel_teacher,purchaseService.sel_teacherNumber(department,purchase,fbegintime,fovertime,major));
        return jsonTemplate.toString();
    }

    //   审核查询
    @RequestMapping("/anotherSel")
    @ResponseBody
    public String anotherSel(@RequestParam("department") String department, purchase purchase, @RequestParam("time")String time, @RequestParam("limit")Integer limit, @RequestParam("page")Integer page,HttpServletRequest httpServletRequest){
        String [] alltime;
        String fbegintime="";
        String fovertime="";
        if(ValidateUtil.isNotEmpty(time)){
            alltime=time.split(" - ");
            fbegintime=alltime[0];
            fovertime=alltime[1];
        }
        User user=(User) httpServletRequest.getSession().getAttribute("user");
        List<purchase> sel_teacher=new ArrayList<>();
        sel_teacher =purchaseService.another_sel(user,purchase,limit,fbegintime,fovertime,page,purchase.getStatus(),department);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (com.haocai.module.purchase.vo.purchase purchase1 : sel_teacher) {
            purchase1.setStartTime(sdf.format(purchase1.getAuditordate()));
        }
        for (com.haocai.module.purchase.vo.purchase purchase1 : sel_teacher) {

//            BigDecimal savg = new BigDecimal(purchase1.getStudentsavg());
//            BigDecimal num = new BigDecimal(purchase1.getStudentnumber());
//            BigDecimal prices ;
//            prices=price.multiply(num);
            //number1

//            purchase1.setNumber(purchase1.getStudentsavg()*purchase1.getStudentnumber());
            BigDecimal price1 = new BigDecimal(String.valueOf(purchase1.getPrice()));
            BigDecimal studentnumber = new BigDecimal(String.valueOf(purchase1.getStudentnumber()));
            BigDecimal studentsavg = new BigDecimal(String.valueOf(purchase1.getStudentsavg()));
            BigDecimal all =BigDecimal.ZERO;
            all=price1.multiply(studentnumber).multiply(studentsavg);
            purchase1.setAllPrices(String.valueOf(all));
            String num = String.valueOf(studentnumber.multiply(studentsavg));
            if (num.indexOf(".") > 0) {//判断是否有小数点
                num = num.replaceAll("0+?$", "");//去掉多余的0
                num = num.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
            purchase1.setNumber1(num);
        }

        JsonTemplate jsonTemplate=new JsonTemplate("查询成功",sel_teacher,purchaseService.another_selNumber(user,purchase,limit,fbegintime,fovertime,page,purchase.getStatus(),department));
        return jsonTemplate.toString();
    }


    @RequestMapping("/anotherSelprice")
    @ResponseBody
    public String anotherSelprice(@RequestParam("department") String department, purchase purchase, @RequestParam("time")String time,@RequestParam("major") String major, HttpServletRequest httpServletRequest){
        String [] alltime;
        String fbegintime="";
        String fovertime="";
        if(ValidateUtil.isNotEmpty(time)){
            alltime=time.split(" - ");
            fbegintime=alltime[0];
            fovertime=alltime[1];
        }
        User user=(User) httpServletRequest.getSession().getAttribute("user");
        List<purchase> sel_teacher=new ArrayList<>();
        sel_teacher =purchaseService.another_selprice(user,purchase,fbegintime,fovertime,purchase.getStatus(),department,major);
        BigDecimal all =BigDecimal.ZERO;
        BigDecimal allPri =BigDecimal.ZERO;
        for (com.haocai.module.purchase.vo.purchase purchase1 : sel_teacher) {
            BigDecimal price1 = new BigDecimal(String.valueOf(purchase1.getPrice()));
            BigDecimal studentnumber = new BigDecimal(String.valueOf(purchase1.getStudentnumber()));
            BigDecimal studentsavg = new BigDecimal(String.valueOf(purchase1.getStudentsavg()));
            all=price1.multiply(studentnumber).multiply(studentsavg);
          allPri=allPri.add(all);
        }
        String priceAll=String.valueOf(allPri);
        return priceAll.toString();
    }
    @RequestMapping("/selTeacherPrecie")
    @ResponseBody
    public String selTeacherPrecie(@RequestParam("department") String department, purchase purchase, @RequestParam("time")String time,@RequestParam("major") String major, HttpServletRequest httpServletRequest)throws JsonProcessingException{
        String [] alltime;
        String fbegintime="";
        String fovertime="";
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        purchase.setOperator(user.getAccount());
        if(ValidateUtil.isNotEmpty(time)){
            alltime=time.split(" - ");
            fbegintime=alltime[0];
            fovertime=alltime[1];
        }
        List<purchase> sel_teacher=new ArrayList<>();
        sel_teacher =purchaseService.sel_teacherprice(department,purchase,fbegintime,fovertime,major);
        BigDecimal all =BigDecimal.ZERO;
        BigDecimal allPri1 =BigDecimal.ZERO;
        for (com.haocai.module.purchase.vo.purchase purchase1 : sel_teacher) {
            BigDecimal price1 = new BigDecimal(String.valueOf(purchase1.getPrice()));
            BigDecimal studentnumber = new BigDecimal(String.valueOf(purchase1.getStudentnumber()));
            BigDecimal studentsavg = new BigDecimal(String.valueOf(purchase1.getStudentsavg()));
            all=price1.multiply(studentnumber).multiply(studentsavg);
            allPri1=allPri1.add(all);
        }
        String allPri = String.valueOf(allPri1);
        return allPri.toString();
    }

//    审核页面
    @RequestMapping("/shenhepage")
    @ResponseBody
    public  String shenhepage(@RequestParam("purchaseid") String purchaseid,@RequestParam("page")Integer page,@RequestParam("limit")Integer limit)throws JsonProcessingException{
        List<Map<String, Object>> shenhepage = purchaseService.shenhepage(purchaseid);
        return new JsonTemplate("查询成功",shenhepage,shenhepage.size()).toString();
    }


//    查所有、总条数
    @RequestMapping("/sel_allnumber")
    @ResponseBody
    public String sel_allnumber(@RequestParam("limit")Integer limit,@RequestParam("page")Integer page) throws JsonProcessingException{
        int number=purchaseService.sel_purchasenumber();
        List<purchase> all=purchaseService.sel_purchaseall(limit,page);
        HashMap<String,Object> map=new HashMap<>();
        map.put("all",all);
        map.put("number",number);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        return json;
    }




    @RequestMapping("/getUserDep")
    @ResponseBody
    public  String getUserDep(HttpServletRequest servletRequest){
        User user= (User) servletRequest.getSession().getAttribute("user");
         String  as=  user.getDeptName();
        return user.getDeptName();
    }

//汇总金额
    @RequestMapping("/getAll")
    @ResponseBody
    public  Object  getAll(purchase purchase,HttpServletRequest httpServletRequest){
        BigDecimal allPri = (BigDecimal) httpServletRequest.getSession().getAttribute("Aprice");
        httpServletRequest.getSession().removeAttribute("Aprice");
        return  allPri;
    }


//    查list
    @RequestMapping("/selList")
    @ResponseBody
    public String SelList(HttpServletRequest r)throws  JsonProcessingException{
        User user= (User) r.getSession().getAttribute("user");
        String department= user.getDeptName();
        String major1 = purchaseService.major(user.getName());
        List<purchase> purchasename = new ArrayList<>();
        String[] split = major1.split(",");
        for (String s : split) {
          purchasename.addAll(purchaseService.purchasename(department,s));
        }
        return new ObjectMapper().writeValueAsString(purchasename);
    }




//    角色、审核状态
    @RequestMapping("/namestatus")
    @ResponseBody
    public String namestatus(HttpServletRequest httpServletRequest)throws JsonProcessingException{
        User user=(User) httpServletRequest.getSession().getAttribute("user");
        return new ObjectMapper().writeValueAsString(purchaseService.namestatus(user));
    }


//    审核意见
    @RequestMapping("/yijian")
    @ResponseBody
    public String shenheyijian(@RequestParam("purchaseid") String purchaseid)throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(purchaseService.shenheyijian(purchaseid));
    }

    //    审核退回
    @RequestMapping("/shenhetuihui")
    @ResponseBody
    public  int shenhetuihui(@RequestParam("purchaseid") String purchaseid,HttpServletRequest httpServletRequest,@RequestParam(value = "opinion",required = false) String opinion){
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        return purchaseService.shenhetuihui(opinion, purchaseid, user);
    }



//    审核成功
@RequestMapping("/shenhe")
@ResponseBody
    public int shenhe(@RequestParam("purchaseid") String purchaseid,HttpServletRequest httpServletRequest,@RequestParam(value = "opinion",required = false) String opinion){
        User user =(User)httpServletRequest.getSession().getAttribute("user");
        return purchaseService.shenhe(purchaseid,user,opinion);
    }
    //     重新修改
    @RequestMapping("/Redit")
    @ResponseBody
    public String Redit(@RequestBody purchase purchase,HttpServletRequest httpServletRequest){
        User user =(User)httpServletRequest.getSession().getAttribute("user");
        int shenhe = purchaseService.shenAgain(purchase.getPurchaseid(), user);
        int i = purchaseService.updateStudentNumber(purchase.getPurchaseid(), purchase.getStudentnumber());
        String l="";
        if(shenhe==1){
            l="修改成功";
        }else{
            l="修改失败";
        }
        return l;
    }
    @RequestMapping("/Redit1")
    @ResponseBody
    public int Redit1(String purchaseid,HttpServletRequest httpServletRequest){
        User user =(User)httpServletRequest.getSession().getAttribute("user");
        int shenhe = purchaseService.shenAgain(purchaseid, user);
        return shenhe;
    }

    @RequestMapping("/deleteALl")
    @ResponseBody
    public int deleteAll( String purchaseid,String consumablesid,HttpServletRequest httpServletRequest){
        String[] split = purchaseid.split(",");
        int i=1;
        for (String s : split) {
            i = purchaseService.deleteAll(s);
            int i1 = purchaseService.deleteAllP(s);
        }
        String[] split1 = consumablesid.split(",");
        for (String s : split1) {
          i=  purchaseService.deletepC(s);
        }
        return i;
    }

    @RequestMapping("/ViewExistence")
    @ResponseBody
    public int Existence(String consumablesid, HttpServletRequest httpServletRequest){
        User user =(User)httpServletRequest.getSession().getAttribute("user");
        String s = purchaseService.ListDep(consumablesid,user.getName());
        int i=0;
        if (s!=null){
            i=1;
        }
        return i;
    }


    @RequestMapping("/pShenHe")
    @ResponseBody
    public Object pShenHe(String purchaseid)throws JsonProcessingException {
        return new JsonTemplate("1",purchaseService.pShenAll(purchaseid),1).toString();
    }

    @RequestMapping("/PshenPass")
    @ResponseBody
    public Object PshenPass(@RequestBody purchase purchase,HttpServletRequest httpServletRequest) {
            User user =(User)httpServletRequest.getSession().getAttribute("user");
        int i = purchaseService.pShenHePass(purchase.getpShenId(), user, purchase.getOpinion());
        return new JsonTemplate("1",null,1).toString();
    }
    @RequestMapping("/PshenBack")
    @ResponseBody
    public Object PshenBack(@RequestBody purchase purchase,HttpServletRequest httpServletRequest) {
        User user =(User)httpServletRequest.getSession().getAttribute("user");
        int i = purchaseService.pShenHeBack(purchase.getOpinion(), purchase.getpShenId(), user);
        return new JsonTemplate("1",null,1).toString();
    }

//    excel导入
    @RequestMapping("/daoru")
    @ResponseBody
    public String daoru(MultipartFile file,HttpServletRequest httpServletRequest)throws JsonProcessingException{
        String b="";
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        HashMap<String,Object> map=new HashMap<>();
        if (!file.isEmpty()) {
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            String a = file.getOriginalFilename().substring(begin, last);
            if(a.endsWith(".xls")){
               map=  purchaseService.daoru(file,user);
                b="成功";
            }
            }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        return json;

    }

    @RequestMapping("/test")
    @ResponseBody
    public String test()throws  JsonProcessingException{
        String s = purchaseService.ListDep("1", "1");
        return new ObjectMapper().writeValueAsString(s);
    }


//    模板
    @RequestMapping("/moban")
    @ResponseBody
    public String moban(String major,HttpServletRequest httpServletRequest)throws  JsonProcessingException{
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        String name=user.getName();
        if (major==""){
            String major1 = purchaseService.major(user.getName());
            if (major1==null){
                return new ObjectMapper().writeValueAsString(null);
            }
            String[] split = major1.split(",");
            ArrayList<Object> list = new ArrayList<>();
            for (String s : split) {
                list.add(s);
            }
            List<purchase> moban = purchaseService.moban(user.getDeptName(), (String) list.get(0));
            for (purchase purchase : moban) {
                String regex="([0-9]\\d*\\.?\\d+)|(0\\.\\d*[0-9])|(\\d+)";
                Pattern pattern=Pattern.compile(regex);
                Matcher matcher=pattern.matcher(purchase.getNumberMark());
                while(matcher.find()) {
                    Float group = Float.valueOf(matcher.group());
                    purchase.setNumber(group);
                }
                purchase.setStudentnumber((float) 0);
            }
            return new ObjectMapper().writeValueAsString(moban);
        }

        List<purchase> moban = purchaseService.moban(user.getDeptName(),major);
        for (purchase purchase : moban) {
            String regex="([0-9]\\d*\\.?\\d+)|(0\\.\\d*[0-9])|(\\d+)";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(purchase.getNumberMark());
            while(matcher.find()) {
                Float group = Float.valueOf(matcher.group());
                purchase.setNumber(group);
            }
            purchase.setStudentnumber((float) 0);
        }
        if (moban.isEmpty()){
            return new ObjectMapper().writeValueAsString(null);
        }
        return new ObjectMapper().writeValueAsString(moban);
    }

    @RequestMapping("/mobanDep")
    @ResponseBody
    public String mobanDep(String department,HttpServletRequest httpServletRequest)throws  JsonProcessingException{
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        String name=user.getName();
        if (department=="") return new ObjectMapper().writeValueAsString(null);
        System.out.println();
        if (user.getRoleName().equals("二级学院管理员")||user.getRoleName().equals("二级学院教学院长")){
            if(!department.equals(user.getDeptName())) return new ObjectMapper().writeValueAsString("dep") ;
        }

        List<purchase> moban = purchaseService.mobanDepartment(department);
        if (moban.isEmpty()){
            return new ObjectMapper().writeValueAsString(null);
        }
        for (com.haocai.module.purchase.vo.purchase purchase1 : moban) {
            BigDecimal price1 = new BigDecimal(String.valueOf(purchase1.getPrice()));
            BigDecimal studentnumber = new BigDecimal(String.valueOf(purchase1.getStudentnumber()));
            BigDecimal studentsavg = new BigDecimal(String.valueOf(purchase1.getStudentsavg()));
            BigDecimal all =BigDecimal.ZERO;
            all=price1.multiply(studentnumber).multiply(studentsavg);
            purchase1.setAllPrices(String.valueOf(all));
            String num = String.valueOf(studentnumber.multiply(studentsavg));
            if (num.indexOf(".") > 0) {//判断是否有小数点
                num = num.replaceAll("0+?$", "");//去掉多余的0
                num = num.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
            purchase1.setNumber1(num);        }

        return new ObjectMapper().writeValueAsString(moban);
    }

    @RequestMapping("/major")
    @ResponseBody
    public Object major(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        String major = purchaseService.major(user.getName());
        if (major==null){
            return new ObjectMapper().writeValueAsString(null);
        }
        String[] split = major.split(",");
        ArrayList<Object> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return new ObjectMapper().writeValueAsString(list);
    }
    @RequestMapping("/majorShen")
    @ResponseBody
    public Object majorShen(String department,HttpServletRequest httpServletRequest) throws JsonProcessingException {
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        if (department==""){
            department=user.getDeptName();
        }
        List<String> major = purchaseService.majorShen(department);
        ArrayList<Object> list = new ArrayList<>();
        for (String s : major) {
            list.add(s);
        }
        return new ObjectMapper().writeValueAsString(list);
    }


    @RequestMapping("/dep")
    @ResponseBody
    public Object dep(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        return new ObjectMapper().writeValueAsString(user.getDeptName());
    }


//    查询采购所有信息
    @RequestMapping("/selpurchaseall")
    @ResponseBody
    public String selpurchaseall(@RequestParam("limit")Integer limit, @RequestParam("page")Integer page,@RequestParam("department")String department,@RequestParam("timeLimit")String timeLimit)throws JsonProcessingException{
        HashMap<String, Object> map = purchaseService.selpurchaseall(department, limit, page, timeLimit);
        return new JsonTemplate("采购记录",map.get("data"),(int)map.get("count")).toString();
    }
//    教务处查询
@RequestMapping("/jwcsel")
@ResponseBody
public String jwcsel(HttpServletRequest httpServletRequest)throws JsonProcessingException{
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        String department=user.getDeptName();
    return new ObjectMapper().writeValueAsString(purchaseService.jwcsel(department));
}
//采购中心查询
    @RequestMapping("/cgzxsel")
    @ResponseBody
    public String cgzxsel()throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(purchaseService.cgzxsel());
    }

    //判断是否可以修改
    @ResponseBody
    @RequestMapping("/judgmentStuts")
    public  int judgmentStuts(int id){
        String s = purchaseService.judgmentStuts(id);
        int a= Integer.parseInt(s);
        return a;
    }

    @ResponseBody
    @RequestMapping("/judgmentshenhe")
    public  int judgmentshenhe(String purchaseid,HttpServletRequest httpServletRequest){
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        String s = purchaseService.judgmentshenhe(purchaseid,user.getName());
        int a= Integer.parseInt(s);
        return a;
    }
}
