package com.haocai.module.inCon.service.serviceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haocai.module.inCon.dao.InConDao;
import com.haocai.module.inCon.service.InConService;
import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.inCon.vo.result.InConVoResult;
import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.outCon.vo.OutReviewVo;
import com.haocai.module.outCon.vo.param.OutVoParam;
import com.haocai.module.system.vo.User;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class InConServiceImpl implements InConService {
    @Autowired
    private InConDao inConDao;

    @Override
    public InConVoResult getData(InConVoParam inConVoParam) {
        inConVoParam.setPage((inConVoParam.getPage()-1)* inConVoParam.getLimit());
        List<InConVo> list= inConDao.getData(inConVoParam);
        System.err.println(inConVoParam.getStatus_z());
        int count=inConDao.getCount(inConVoParam);
        for (InConVo i:list) {
            InConVoParam param=inConDao.getId(i);
            if (param!=null){
                User user=inConDao.getName(param);
                i.setShenHeName(user.getName());
            }else {
                InConVoParam voParam=inConDao.getTweId(i);
                User user=inConDao.getName(voParam);
                if (user!=null){
                    i.setShenHeName(user.getName());
                }
            }
            User user=inConDao.getName(param);
            if (user!=null){
                i.setShenHeName(user.getName());
            }
        }
        InConVoResult inConVoResult=new InConVoResult();
        inConVoResult.setList(list);
        inConVoResult.setCount(count);

        return inConVoResult;
    }

    @Override
    public int addReview(Map<String,String> map) {
        String review_id=null; //审核编号
          String[] ids=  map.get("ids").split(",");
            for (String id : ids) {
                review_id= UUID.randomUUID().toString(); //审核编号
                //修改出库表状态和添加审核号
                inConDao.setConStatus(id,review_id);
                //添加审核表记录
                InConVoParam outVoParam=new InConVoParam();

                outVoParam.setReview_id(review_id);//设置审核标号
                outVoParam.setProject_name("入库审核");
                outVoParam.setId(UUID.randomUUID().toString());
                outVoParam.setRoleId(map.get("userId1"));
                outVoParam.setBefore_re(null);
                outVoParam.setAfter_re(map.get("userId2"));
                outVoParam.setStatus("0");
                outVoParam.setRole(map.get("role1"));
                inConDao.addReview(outVoParam);//添加第一条审核记录
                outVoParam.setId(UUID.randomUUID().toString());
                outVoParam.setRoleId(map.get("userId2"));
                outVoParam.setBefore_re(map.get("userId1"));
                outVoParam.setAfter_re(null);
                outVoParam.setStatus(null);
                outVoParam.setRole(map.get("role2"));
                inConDao.addReview(outVoParam);//添加第二条审核记录
            }
        return 1;
    }

    @Override
    public int del(String[] ids) {
        List<OutReviewVo> ReviewVos= inConDao.getReviewIds(Arrays.asList(ids));
        List reviewIds=new ArrayList();
        for (OutReviewVo reviewVo : ReviewVos) {
           String reId= reviewVo.getReview_id();
           if (reId==null){
               continue;
           }
           reviewIds.add(reId);
        }
        if (!reviewIds.isEmpty()){
            inConDao.delReviewById(reviewIds);
        }
        return  inConDao.del(ids);
    }

    @Override
    public int addCon(InConVoParam param) {
        Calendar calendar = Calendar.getInstance();
        String inTime = calendar.get(Calendar.YEAR)+"";
        param.setYearin(inTime);
        BigDecimal price=new BigDecimal(param.getPrice().toString());
        BigDecimal number=new BigDecimal(param.getNumber().toString());
        param.setPrices(price.multiply(number).toString());
        inConDao.addCon(param);
        return 0;
    }

    @Override
    public List<InConVo> getCons() {
        return inConDao.getCons();
    }
    @Override
    public Object InExcel(String fileUrl, User user) {
        List<InConVo> dataList=new ArrayList<>();//存储正确数据的list
//        List<HashMap<String,Object>> errorDataList=new ArrayList<>();
        List<String> list=new ArrayList<>();//存储字段名的list
        list.add("name");
        list.add("number");
        list.add("price");
        list.add("attribute");
        list.add("type");
        list.add("con_type");
        list.add("standards");
        list.add("receiptNo");
        list.add("conTypeId");
        Calendar calendar = Calendar.getInstance();
        String inTime = calendar.get(Calendar.YEAR)+"";
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(new FileInputStream(fileUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取第一个工作表数据
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //获取最后一行的num，即总行数。此处从0开始
        int maxRow = sheet.getLastRowNum();
        out:for (int row = 3; row <= maxRow; row++) {//第三行开始获取数据
//           boolean continues=false;//判断是否跳出此行默认关闭
            //获取最后的有效数据列号
            HashMap<String,Object> hashMap=new HashMap<>();//存放每一行的数据
            String conType = "";
            int hang = row+1;
            for (int rol = 10; rol <=18; rol++){
                String data="";

                if(rol!=18){
                    try{
                        data=sheet.getRow(row).getCell(rol).toString().trim();
                    }catch (NullPointerException e){
                        break out;
                    }

                }
                if(rol==10){
                    if(data.equals("")){
                        break out;
                    }
                }
//                if (data==null||"".equals(data)){
//                        continues=true;
//                }
//                if (data.indexOf(".")!=-1&&rol!=11&&rol!=12&&rol!=16){
//                    data=data.substring(0,data.indexOf("."));
//                }
                if(rol == 13){
                    if(!data.equals("1") && !data.equals("2")){
                        //throw new RuntimeException();
                        return "第"+hang+"行属性数据格式不对";
                    }
                }else if(rol == 14){
                    if(!data.equals("1") && !data.equals("2") && !data.equals("3") && !data.equals("4") && !data.equals("5") && !data.equals("6")
                            && !data.equals("7") && !data.equals("8") && !data.equals("9") && !data.equals("10") && !data.equals("11")){
                        //throw new RuntimeException();
                        return "第"+hang+"行类别数据格式不对";
                    }
                }else if(rol == 15){
                    if(!data.equals("1") && !data.equals("2") && !data.equals("3") && !data.equals("4")){
                        //throw new RuntimeException();
                        return "第"+hang+"行库类别数据格式不对";
                    }else{
                       conType = data;
                    }
                }else if(rol == 18){
                    if(!conType.equals("3")){
                        data=sheet.getRow(row).getCell(rol).toString().trim();
                        String id = inConDao.checkConTypeName(conType, data);
                        if(id == null){
                            //throw new RuntimeException();
                            return "第"+hang+"行大赛名称/实训名称/设备维护保养名称数据不存在";
                        }else{
                            data = id;
                        }
                    }

                }
                hashMap.put(list.get(rol-10),data);
            }
//            if (continues){
//                errorDataList.add(hashMap);
//                continue;
//            }

            hashMap.put("department",user.getDeptName());
            hashMap.put("applicant",user.getAccount());
            hashMap.put("yearin",inTime);
            BigDecimal price;
            BigDecimal number;
            try{
                price=new BigDecimal(hashMap.get("price").toString());
            }catch (Exception e){
                return "第"+hang+"行价格数据格式不对";
            }
            try{
                number=new BigDecimal(hashMap.get("number").toString());
            }catch (Exception e){
                return "第"+hang+"行数量数据格式不对";
            }
            hashMap.put("prices",price.multiply(number));
            JSONObject jsonObject=new JSONObject(hashMap);
           dataList.add(JSON.parseObject(jsonObject.toString(),InConVo.class));
        }
        try {
            xssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            inConDao.excelAddCon(dataList);
        return "导入成功";
    }


    @Override
    public Integer inCon(List<InConVo> cons) {
        for (InConVo con : cons) {
            System.err.println("入库的信息"+con.toString());
            //修改入库状态
            inConDao.setInConStatus(con);
            //查询重复
           List<InConVo> list= inConDao.selectCon(con);
            System.err.println("重复的数据"+list.toString());
           if (list.size()==0){//如果没有就添加
                inConDao.inCon(con);
           }
           else {
               inConDao.addConNumber(con);
           }
        }
        return 2;
    }
}
