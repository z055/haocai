package com.haocai.module.consumableslist.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.haocai.module.consumableslist.dao.ConsumableslistMapper;
import com.haocai.module.consumableslist.service.ConsumableslistService;
import com.haocai.module.consumableslist.vo.Consumableslist;
import com.haocai.module.consumableslist.vo.ErrorExcel;
import com.haocai.module.course.vo.Course;
import com.haocai.module.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsumablesListListener extends AnalysisEventListener<Consumableslist> {
    @Autowired
    private ConsumableslistService consumableslistService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ConsumableslistMapper consumableslistMapper;


    public ConsumablesListListener(ConsumableslistService consumableslistService, HttpServletRequest request, ConsumableslistMapper consumableslistMapper) {
        this.consumableslistService = consumableslistService;
        this.consumableslistMapper = consumableslistMapper;
        this.request = request;
    }

    //  一行行读取excel内容
    @Override
    public void invoke(Consumableslist consumableslist, AnalysisContext analysisContext) {
        if (consumableslist.getName() != null) {
            User user = (User) request.getSession().getAttribute("user");
            ErrorExcel errorExcel = new ErrorExcel();
            List<String> list = new ArrayList<>();

            boolean save = true;
            //List<Course> resultTraining=consumableslistService.selectCourseByTraining(consumableslist);
            //List<Consumableslist> result=consumableslistMapper.addReCheckData(consumableslist);

            if (!StringUtils.isNumeric(consumableslist.getAttribute())) {
                list.add("属性值为数字代号非其他文字");
                save = false;
            } else if (!StringUtils.isNumeric(consumableslist.getType())) {
                list.add("类别为数字代号非其他文字");
                save = false;
            } else if (consumableslist.getAttribute() == null || consumableslist.getDepartment() == null || consumableslist.getType() == null || consumableslist.getStandards() == null || consumableslist.getTraining() == null || consumableslist.getUnit() == null || consumableslist.getPrice() == null || consumableslist.getNumber() == null || consumableslist.getSemester() == null) {
                list.add("存在为空的数据请填写完整");
                save = false;
            }

//           else if (result.size()!=0){
//               list.add("该数据存在重复");
//               save=false;
//           }
//           else if (resultTraining.size()==0){
//               list.add("该实训项目在课程管理不存在");
//               save=false;
//           }
            String oldSemester = consumableslist.getSemester();
            String newSemester = oldSemester.replaceAll("，", ",");
            consumableslist.setSemester(newSemester);
            if (!save) {
                String errorString = String.join(",", list);
                errorExcel.setErrormsg(errorString);
                errorExcel.setName(consumableslist.getName());
                errorExcel.setAttribute(consumableslist.getAttribute());
                errorExcel.setType(consumableslist.getType());
                errorExcel.setStandards(consumableslist.getStandards());
                errorExcel.setUnit(consumableslist.getUnit());
                errorExcel.setPrice(consumableslist.getPrice());
                errorExcel.setNumber(consumableslist.getNumber());
                errorExcel.setTraining(consumableslist.getTraining());
                errorExcel.setDepartment(consumableslist.getDepartment());
                errorExcel.setSemester(newSemester);
                errorExcel.setUserid(user.getUserId().toString());
                consumableslistService.addErrorExcel(errorExcel);
            }

            if (save) {
                System.out.println(consumableslist);
                if (consumableslistMapper.addReCheckData(consumableslist).size() != 0) {
                    System.out.println("数据库存在" + consumableslist.getName());
                } else {
                    saveData(consumableslist, request);
                }
            }

        }

    }

    private void saveData(Consumableslist consumableslist, HttpServletRequest request) {
        consumableslistService.importExcel(consumableslist, request);
    }

    //  读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }

    //  读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("存储完毕");
    }
}
