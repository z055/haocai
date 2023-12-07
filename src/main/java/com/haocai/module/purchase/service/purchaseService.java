package com.haocai.module.purchase.service;

import com.haocai.module.purchase.vo.purchase;
import com.haocai.module.system.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface purchaseService {
    List<purchase> purchaselist(String consumablesid,String department);
    List<purchase> changepurchaselist(String consumablesid,Integer id);
    int updatepurchase(purchase purchase);
    List<purchase> sel_all(String purchaseid);
    int purchasecheck(purchase purchase, User user);
    int deletepurchase(Integer id);
    int deletepC(String id);
    int sub_purchase(String id,User user,String poinion,String auditoid);
    List<purchase> sel_teacher(String department,purchase purchase,String begintime,String overtime,Integer limit,Integer page,String major);

    List<purchase> another_sel(User user, purchase purchase,Integer limit, String begintime,String overtime,Integer page,String status,String department);
    List<purchase> another_selprice(User user, purchase purchase, String begintime,String overtime,String status,String department,String major);
    int another_selNumber(User user, purchase purchase,Integer limit, String begintime,String overtime,Integer page,String status,String department);


    List<purchase> sel_teacherprice(String department,purchase purchase,String begintime,String overtime,String major);

    List<Map<String,Object>> shenhepage(String purchaseid);
    List<purchase> sel_purchaseall(Integer limit,Integer page);
    int  sel_purchasenumber();
    int shenhetuihui(String opinion, String purchaseid,User user);
    int pShenHeBack(String opinion, String pShenId,User user);
    List<purchase> purchasename(String department, String major);

    String major(String name);
    List<String>  majorShen(String department);

    int SelListNumber();
    String selRname(int rid);
//    List<purchase> selList(String department);
    int sel_teacherNumber(String department,purchase purchase,String fbegintime,String fovertime,String major);
    List<Map<String,String>> namestatus(User user);
    List<purchase> shenheyijian(String purchaseid);
    int shenhe(String purchaseid,User user,String opinion);

    int shenAgain(String purchaseid,User user);

    int updateStudentNumber(@Param("purchaseid")String purchaseid,@Param("studentNumber")Float studentNumber);

    int deleteAll(String purchaseid);
    int deleteAllP(String purchaseid);
    //批量审核（通过）
    int pShenHePass(String pShenId,User user,String opinion);
    HashMap daoru(MultipartFile file, User user);
    List<purchase> moban(String department,String major);

    List<purchase> mobanDepartment(String department);

    HashMap<String,Object>selpurchaseall(String department,Integer limit,Integer page,String timeLimit);
    List<purchase> jwcsel(String department);
    List<purchase> cgzxsel();

    String judgmentStuts(int id);
    String judgmentshenhe(String purchaseid,String name);

    List<purchase> pShenAll(@Param("purchaseid")String purchaseid);

    String ListDep(@Param("consumablesid")String consumablesid, @Param("operatorname")String operatorname);
}
