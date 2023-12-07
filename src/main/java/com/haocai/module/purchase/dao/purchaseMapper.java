package com.haocai.module.purchase.dao;

import com.haocai.module.purchase.vo.purchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface purchaseMapper {
    //获取耗材名称
    List<purchase> purchasename(@Param("department")String department,@Param("major")String major);
    List<purchase> purchaselist(@Param("consumablesid")String consumablesid,@Param("department")String department);
    List<purchase> changepurchaselist(@Param("consumablesid")String consumablesid,@Param("id")Integer id);
    int updatepurchase(@Param("purchase")purchase purchase);
    List<purchase> sel_all(@Param("purchaseid")String purchaseid);
    int purchasecheck(@Param("purchase")purchase purchase);
    int deletepurchase(Integer id);
    int deletepC(String id);
    int deleteAllP( String purchaseid);
    int deleteAll(String purchaseid);
    int sub_purchase(@Param("id") Integer id,@Param("status") String status,@Param("department") String department);
    int addtablein(@Param("purchase")purchase purchase);
    int log_purchase(@Param("purchase")purchase purchase);
    List<purchase> sel_teacher(@Param("department") String department,@Param("purchase") purchase purchase,
                               @Param("begintime")String begintime,@Param("overtime")String overtime,@Param("originate")Integer originate,
                               @Param("end")Integer end,
                               @Param("major") String major);


    List<purchase> another_sel(@Param("department")String department,@Param("purchase") purchase purchase,
                               @Param("begintime")String begintime,@Param("overtime")String overtime,
                               @Param("originate")Integer originate, @Param("end")Integer end,
                               @Param("major") String major);

    int updateStudentNumber(@Param("purchaseid")String purchaseid,@Param("studentNumber")Float studentNumber);


    int another_selNumber(@Param("department")String department,@Param("purchase") purchase purchase,
                               @Param("begintime")String begintime,@Param("overtime")String overtime,
                               @Param("major") String major);


    List<purchase> another_selprice(@Param("department")String department,@Param("purchase") purchase purchase,
                               @Param("begintime")String begintime,@Param("overtime")String overtime,
                               @Param("major")String major);

    List<purchase> sel_teacherprice(@Param("department") String department,@Param("purchase") purchase purchase,
                                    @Param("begintime")String begintime,@Param("overtime")String overtime,@Param("major")String major);

    int shenheliucheng(@Param("id1")String id1,@Param("id2")String id2,@Param("id3")String id3,@Param("id4")String id4,@Param("purchaseid")String purchaseid,@Param("status")String status,@Param("account")String account,@Param("department")String department,@Param("operator") String operator,@Param("one") String one,@Param("two")String two,@Param("three")String three,@Param("four")String four);
    int changeliucheng(@Param("purchaseid")String purchaseid,@Param("status")String status,@Param("role")String role);
    String  sel_purchaseid(@Param("id")Integer id);
    List<purchase> shenhepage(@Param("purchaseid")String purchaseid);
    int sel_purchaseallnumber();
    List<purchase>sel_purchaseall(@Param("orginate")Integer originate,@Param("end")Integer end);
    int addopinion(@Param("purchaseid")String purchaseid,@Param("opinion")String opinion ,@Param("account")String account,@Param("auditorname")String auditorname);

    //清空意见

    int Pclearopinion(@Param("purchaseid")String purchaseid);
    int Rclearopinion(@Param("purchaseid")String purchaseid);


    //查看目录是否存在
    String ListDep(@Param("consumablesid")String consumablesid, @Param("operatorname")String operatorname);


    //实体类String
    String  ListDep1(@Param("consumablesid")String consumablesid, @Param("operatorname")String operatorname);

    int sel_status(@Param("purchaseid")String purchaseid);
    String gocontinue(String role);
    String back(String role);
    List<purchase> getall(@Param("department")String department,@Param("status")String status,@Param("operator")String operator);

    List<purchase> pShenAll(@Param("purchaseid")String purchaseid);
    int sel_listnumber();
    String major(String name);
    List<String> majorShen(String department);
    String selRname(@Param("rid")Integer rid);
    List<purchase> selList(@Param("department")String department);
    int sel_teacherNumber(@Param("department") String department,@Param("purchase") purchase purchase,@Param("begintime")String begintime,@Param("overtime")String overtime,@Param("major")String major);
    purchase sel_purchase_id(Integer id);
    int changepurchaseid(@Param("purchaseid") String purchaseid,@Param("id") Integer id);
    List<Map<String,Object>> selstudent(@Param("purchaseid") String purchaseid);
    int selprice(Integer id,String no);
    String seltype(String no);
    String selno(Integer id);
    int updatestatus(@Param("purchaseid") String purchaseid,@Param("status") String status);
    int delliucheng(@Param("purchaseid") String purchaseid);
    int delopinion(@Param("purchaseid")String purchaseid);
    int changeopin(@Param("opinion")String opinion,@Param("purchaseid")String purchaseid);
    List<purchase> selshop(@Param("purchaseid") String purchaseid);
//    String selopinion(@Param("purchaseid")String purchaseid);
    Float selnumber(@Param("consumablesid") String consumablesid,@Param("price") Float price);
    Float selpurnumber(@Param("consumablesid") String consumablesid,@Param("operator") String operator);
    int purchaselistin(@Param("purchase")purchase purchase);
    List<purchase> excel(@Param("department")String department,@Param("major")String major);
    List<purchase> excelDepartment(@Param("department")String department);
    List<purchase> selpurchaseall(@Param("department")String department,@Param("originate")Integer originate,@Param("end")Integer end,@Param("begintime")String begintime,@Param("overtime")String overtime);
    int selpurchaseallCount(@Param("department")String department,@Param("begintime")String begintime,@Param("overtime")String overtime);
    List<purchase> jwcsel(@Param("department") String department);
    List<purchase> cgzxsel();
    //更改采购表 采购表审核状态
    int updatestatu(@Param("statu")String statu,@Param("purchaseid")String purchaseid);
    String auditor(@Param("auditorid")String auditorid);
    String judgmentStuts(int id);
    String judgmentshenhe( @Param("purchaseid") String purchaseid,@Param("name") String name);

    int TuiHuiR( @Param("purchaseid")String purchaseid,@Param("operatorname")String operatorname);
    int TuiHuiP( @Param("purchaseid")String purchaseid);


    //添加意见
    int Ropinion(@Param("opinion")String opinion,@Param("operatorname")String operatorname);
    int Ropinionone(@Param("opinion")String opinion,@Param("operatorname")String operatorname,@Param("purchaseid")String purchaseid);
}
