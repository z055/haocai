package com.haocai.module.sbModule.controller;

import com.haocai.module.outCon.vo.OutConVo;
import com.haocai.module.sbModule.service.SbReviewService;
import com.haocai.module.sbModule.service.SbService;
import com.haocai.module.sbModule.vo.SbVo;
import com.haocai.module.sbModule.vo.param.SbParam;
import com.haocai.module.sbModule.vo.resulet.SbResult;
import com.haocai.module.system.vo.User;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/sbController")
@ResponseBody
public class SbController {
    @Autowired
    private SbService sbService;

    @RequestMapping("/getBorrows")
    public Object getBorrows(SbParam sbVo, HttpSession session) {
        sbVo = (SbParam) ConversionStrUtil.ConversionStr(sbVo);
        User user = (User) session.getAttribute("user");
        sbVo.setOperator(user.getAccount());//外界管理 查看申请人申请的
        sbVo.setLibraryName(String.valueOf(user.getUserId()));
        SbResult result = sbService.getBorrows(sbVo);
        return new JsonTemplate("查询", result.getList(), result.getCount()).toString();
    }

    /**
     * 获取当前登录人所属学院
     * @param session
     * @return
     */
    @RequestMapping("/getDepartment")
    public Object getDepartment(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String deptName = user.getDeptName();
        return new JsonResult(JsonResult.SUCCESS, deptName,null);
    }



    /**
     * 添加借用审核流程
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/addSb")
    public Object addSb(@RequestBody HashMap<String, String> map, HttpSession session) {
        System.err.println("/addSb.Map=====》" + map.toString());
        User user = (User) session.getAttribute("user");
        int number = Integer.parseInt(map.get("number"));//库存
        int outNumber = Integer.parseInt(map.get("outNumber"));//借用数
        if (outNumber <= 0) {
            return new JsonResult(null, null, "借用数量不可低于1");
        }
        int conOutNumber = sbService.getConOutNum(map);//出库数
        System.out.println("/addSb====>" + conOutNumber);
        if (outNumber > number - conOutNumber) {
            return new JsonResult(null, null, "部分耗材已出库，库存不足,您最多可借用" + (number - conOutNumber) + "个");
        }
        map.put("operator", user.getAccount());
        sbService.addSb(map);
        return new JsonResult(null, null, "success");
//            SbParam sbVo= JSON.parseObject(JSON.toJSONString(map),SbParam.class);
//            sbVo.setReview_id(UUID.randomUUID().toString().replace("-", ""));
//            User user = (User) session.getAttribute("user");
//            sbVo.setOperator(user.getAccount());
//            sbService.addSb(sbVo);
//            return new JsonTemplate("1",null,2).toString();
    }

    /**
     * 添加归还申请
     * @param map
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/addGiveBack")
    public Object addGiveBack(@RequestBody HashMap<String, String> map, HttpSession session) {
        System.err.println("/addGiveBack.Map=====》" + map.toString());
        User user = (User) session.getAttribute("user");
        map.put("operator", user.getAccount());
        sbService.addGiveBackSb(map);
        return new JsonResult(null, null, "success");
    }


    @RequestMapping("del")
    public Object del(SbParam sbParam) {
        int result = sbService.delSb(sbParam);
        if (result == 0) {
            return new JsonResult("0", null, "删除成功");
        }
        return new JsonResult("1", null, "删除失败");
    }

    @ResponseBody
    @RequestMapping("/updateSb")
    public Object update(@RequestBody HashMap<String, String> map, HttpSession session) {
        System.err.println(map.toString());
        User user = (User) session.getAttribute("user");
        map.get("number");
        map.put("operator", user.getAccount());
        sbService.updateSbReview(map);
        return new JsonResult(null, null, "success");
    }

    /**
     * 库管员出库
     * @param sbVos
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/outCons")
    public Object outCons(@RequestBody List<SbVo> sbVos, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String roleName = user.getRoleName();
        System.err.println(roleName);
        if (roleName.indexOf("库管员")==-1) {
            System.err.println("!roleName.startsWith(\"库管员\")");
            return new JsonResult("0", null, "权限不足，无法出库");
        }
        System.err.println("/couCons=====》" + sbVos.toString());
        List<SbVo> sbVoList = sbService.out(sbVos);
        if (sbVoList.isEmpty()) {
            return new JsonResult("1", null, "出库成功");
        }
        return new JsonResult("0", sbVoList, "部分耗材库存不足出库失败");
    }

    /**
     * 库管员入库
     * @param sbVos
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/storageCons")
    public Object storageCons(@RequestBody List<SbVo> sbVos, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String roleName = user.getRoleName();
        if (roleName.indexOf("库管员")==-1) {
            return new JsonResult("0", null, "权限不足，无法入库");
        }
        System.err.println("/storageCons.sbVos=====》" + sbVos.toString());
        boolean flag = sbService.storage(sbVos);
        if (flag) {
            return new JsonResult("1", null, "入库成功");
        }
        return new JsonResult("0", null, "入库失败");
    }

//    @ResponseBody
//    @RequestMapping("/shenHeRole")
//    public Object shenHeRole(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        String roleId = user.getRoleId();
//        System.err.println("/shenHeRole=====》" + roleId);
//        if (("1393089806736678914").equals(roleId)) {
//            return new JsonResult("1", null, "库管员");
//        }
//        return new JsonResult("0", null, "其他审核身份");
//    }
}
