package com.haocai.module.inCon.controller;

import com.alibaba.fastjson.JSONObject;
import com.haocai.module.inCon.service.InConService;
import com.haocai.module.inCon.vo.InConVo;
import com.haocai.module.inCon.vo.param.InConVoParam;
import com.haocai.module.inCon.vo.result.InConVoResult;
import com.haocai.module.system.vo.User;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/inCon")
public class InConController {
    @ResponseBody
    @RequestMapping("/getUserRol")
    public Object getUserRole(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String role = user.getRoleName();
        return role;
    }

    @ResponseBody
    @RequestMapping("/getDepartment")
    public Object getDepartment(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String deptName = user.getDeptName();
        return deptName;
    }

    @Autowired
    private InConService inConService;

    @RequestMapping("/getData")
    @ResponseBody
    public Object getData(InConVoParam inConVoParam, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //String[] roleName = user.getRoleName().split(",");
        String roleName = user.getRoleName();
        if(roleName.contains("教务处管理员") || roleName.contains("督导员") || roleName.contains("二级学院教学院长")){
            inConVoParam.setApplicant("");
            if(roleName.contains("二级学院教学院长")){
                inConVoParam.setDepartment(user.getDeptName());
            }
        }else{
            inConVoParam.setApplicant(user.getAccount());
        }
        inConVoParam = (InConVoParam) ConversionStrUtil.ConversionStr(inConVoParam);
        InConVoResult result = inConService.getData(inConVoParam);
        return new JsonTemplate("查询成功", result.getList(), result.getCount()).toString();
    }

    @ResponseBody
    @RequestMapping("/addReview")
    public Object addReview(@RequestBody HashMap<String, String> map) {
        inConService.addReview(map);
        return "0";
    }

    @ResponseBody
    @RequestMapping("conDel")
    public Object conDel(String id) {
        String[] ids = id.split(",");
        int i = inConService.del(ids);
        if (i == ids.length) {
            return new JsonResult("1", null, "删除成功");
        }
        return new JsonResult("0", null, "删除失败");
    }

    @ResponseBody
    @RequestMapping("/getCons")
    public Object getCons() {
        List<InConVo> inConVos = inConService.getCons();
        return new JsonResult("1", inConVos, "获取成功");
    }

    @ResponseBody
    @RequestMapping("/addCon")
    public Object addCon(InConVoParam param, HttpSession session) {
        param = (InConVoParam) ConversionStrUtil.ConversionStr(param);
        User user = (User) session.getAttribute("user");
        String department = user.getDeptName();
        param.setDepartment(department);
        param.setApplicant(user.getAccount());
        return inConService.addCon(param);
    }


    @RequestMapping("/inConExcelDownload")
    @ResponseBody
    public void courseExcelDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //要下载的文件地址
        String path = request.getSession().getServletContext().getRealPath("/download");
        String filename = "入库管理导入模板.xlsx";
        //设置response相应头
        response.reset();//设置页面不缓存
        response.setCharacterEncoding("utf-8");//设置相应编码
        response.setContentType("multipart/form-data");//二进制传输流
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf-8"));
        File file = new File(path, filename);
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(data)) != -1) {
            outputStream.write(data, 0, len);
            outputStream.flush();
        }
        inputStream.close();
        outputStream.close();
    }

    @RequestMapping("/inConExcelImport")
    @ResponseBody
    public Object inConExcelImport(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String hz = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        JSONObject resObj = new JSONObject();
        User user = (User) request.getSession().getAttribute("user");
        if (".xlsx".equals(hz)) {
            String path = request.getSession().getServletContext().getRealPath("/download");
            String fileName = UUID.randomUUID().toString();
            File realPath = new File(path);
            file.transferTo(new File(realPath + "/" + fileName + ".xlsx"));//直接上传  可以使用uuid
            //去读取数据
            String result = (String) inConService.InExcel(realPath + "/" + fileName + ".xlsx", user);
            new File(realPath + "/" + fileName + ".xlsx").delete();
            resObj.put("msg", result);
            return resObj;
        }
        resObj.put("msg", "上传失败格式后缀要为xlsx");
        return resObj;
    }
//    @ResponseBody
//    @RequestMapping("selectCon")
//    public Object selectCon(InConVo param,HttpSession session){
//        param=(InConVo)  ConversionStrUtil.ConversionStr(param);
//        User user=(User) session.getAttribute("user");
//        String department =user.getDeptName();
//        param.setDepartment(department);
//        param=(InConVo) ConversionStrUtil.ConversionStr(param);
//        Integer i = inConService.inCon(param);
//        if (i == 1){
//            return new JsonResult("1",null,"入库成功");
//        }
//        return new JsonResult("0",null,"入库失败");
//    }
    //废弃库管员的入库
//    @ResponseBody
//    @RequestMapping("inCon")
//    public Object inCon(@RequestBody List<InConVo> params, HttpSession session){
//        System.out.println(params.toString());
//               inConService.inCon(params);
//                 return new JsonResult("0",null,"入库完成");
//    }

}
