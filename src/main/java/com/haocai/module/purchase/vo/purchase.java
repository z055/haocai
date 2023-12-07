package com.haocai.module.purchase.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component

public class purchase {
    //    耗材编号
    private String no;
    //    耗材属性
    private String attribute;
    //    耗材名称
    private String name;
    //    耗材类别
    private String type;
    //    型号规格
    private String standards;
    //    单位
    private String unit;
    //    单价
    private Float price;
    //    生均数量
    private Float studentsavg;
    //    实训项目
    private String training;
    //    学期
    private String semester;
    //    二级学院
    private String department;
    //    库存数量
    private Float stock;
    //    学生数量
    private Float studentnumber;
    //    审核状态
    private String status;
    //    耗材数量
    private Float number;
    //用来接收 生均数
    private String numberMark;
    //    ID
    private Integer id;
    //    录入日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatordate;
    //    审核日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditordate;

    private String startTime;
    //    备注
    private String mark;
    //    审核意见
    private String opinion;
    //    操作员姓名
    private String operatorname;
    //    操作员账号
    private String operator;
    //    课程名称
    private String coursename;
    //    审核员姓名
    private String auditorname;
    //    采购id
    private String purchaseid;

    private String pShenIdAll;

    private String consumablesid;
    //流程 0 1 2 3 4 5
    private String statu;

    private  String pShenId;

    // 管理员审核意见
    private  String auditBody;

    private String allPrices;

    private  String  major;

    private  String number1;

    public purchase() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getStudentsavg() {
        return studentsavg;
    }

    public void setStudentsavg(Float studentsavg) {
        this.studentsavg = studentsavg;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Float getStock() {
        return stock;
    }

    public void setStock(Float stock) {
        this.stock = stock;
    }

    public Float getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(Float studentnumber) {
        this.studentnumber = studentnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public String getNumberMark() {
        return numberMark;
    }

    public void setNumberMark(String numberMark) {
        this.numberMark = numberMark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOperatordate() {
        return operatordate;
    }

    public void setOperatordate(Date operatordate) {
        this.operatordate = operatordate;
    }

    public Date getAuditordate() {
        return auditordate;
    }

    public void setAuditordate(Date auditordate) {
        this.auditordate = auditordate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getAuditorname() {
        return auditorname;
    }

    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname;
    }

    public String getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(String purchaseid) {
        this.purchaseid = purchaseid;
    }

    public String getpShenIdAll() {
        return pShenIdAll;
    }

    public void setpShenIdAll(String pShenIdAll) {
        this.pShenIdAll = pShenIdAll;
    }

    public String getConsumablesid() {
        return consumablesid;
    }

    public void setConsumablesid(String consumablesid) {
        this.consumablesid = consumablesid;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getpShenId() {
        return pShenId;
    }

    public void setpShenId(String pShenId) {
        this.pShenId = pShenId;
    }

    public String getAuditBody() {
        return auditBody;
    }

    public void setAuditBody(String auditBody) {
        this.auditBody = auditBody;
    }

    public String getAllPrices() {
        return allPrices;
    }

    public void setAllPrices(String allPrices) {
        this.allPrices = allPrices;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    @Override
    public String toString() {
        return "purchase{" +
                "no='" + no + '\'' +
                ", attribute='" + attribute + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", standards='" + standards + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", studentsavg=" + studentsavg +
                ", training='" + training + '\'' +
                ", semester='" + semester + '\'' +
                ", department='" + department + '\'' +
                ", stock=" + stock +
                ", studentnumber=" + studentnumber +
                ", status='" + status + '\'' +
                ", number=" + number +
                ", numberMark='" + numberMark + '\'' +
                ", id=" + id +
                ", operatordate=" + operatordate +
                ", auditordate=" + auditordate +
                ", startTime='" + startTime + '\'' +
                ", mark='" + mark + '\'' +
                ", opinion='" + opinion + '\'' +
                ", operatorname='" + operatorname + '\'' +
                ", operator='" + operator + '\'' +
                ", coursename='" + coursename + '\'' +
                ", auditorname='" + auditorname + '\'' +
                ", purchaseid='" + purchaseid + '\'' +
                ", pShenIdAll='" + pShenIdAll + '\'' +
                ", consumablesid='" + consumablesid + '\'' +
                ", statu='" + statu + '\'' +
                ", pShenId='" + pShenId + '\'' +
                ", auditBody='" + auditBody + '\'' +
                ", allPrices='" + allPrices + '\'' +
                ", major='" + major + '\'' +
                ", number1='" + number1 + '\'' +
                '}';
    }

    public purchase(String no, String attribute, String name, String type, String standards, String unit, Float price, Float studentsavg, String training, String semester, String department, Float stock, Float studentnumber, String status, Float number, String numberMark, Integer id, Date operatordate, Date auditordate, String startTime, String mark, String opinion, String operatorname, String operator, String coursename, String auditorname, String purchaseid, String pShenIdAll, String consumablesid, String statu, String pShenId, String auditBody, String allPrices, String major, String number1) {
        this.no = no;
        this.attribute = attribute;
        this.name = name;
        this.type = type;
        this.standards = standards;
        this.unit = unit;
        this.price = price;
        this.studentsavg = studentsavg;
        this.training = training;
        this.semester = semester;
        this.department = department;
        this.stock = stock;
        this.studentnumber = studentnumber;
        this.status = status;
        this.number = number;
        this.numberMark = numberMark;
        this.id = id;
        this.operatordate = operatordate;
        this.auditordate = auditordate;
        this.startTime = startTime;
        this.mark = mark;
        this.opinion = opinion;
        this.operatorname = operatorname;
        this.operator = operator;
        this.coursename = coursename;
        this.auditorname = auditorname;
        this.purchaseid = purchaseid;
        this.pShenIdAll = pShenIdAll;
        this.consumablesid = consumablesid;
        this.statu = statu;
        this.pShenId = pShenId;
        this.auditBody = auditBody;
        this.allPrices = allPrices;
        this.major = major;
        this.number1 = number1;
    }
}
