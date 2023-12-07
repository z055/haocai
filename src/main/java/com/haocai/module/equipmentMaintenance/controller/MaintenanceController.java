package com.haocai.module.equipmentMaintenance.controller;

import com.haocai.module.equipmentMaintenance.service.MaintenanceService;
import com.haocai.module.equipmentMaintenance.vo.Maintenance;
import com.haocai.module.equipmentMaintenance.vo.param.MaintenanceParam;
import com.haocai.module.equipmentMaintenance.vo.result.MaintenanceResult;
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

@Controller
@RequestMapping("/maintenance")
@ResponseBody
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    /**
     * 添加设备保养数据
     * @param maintenance
     * @return
     */
    @RequestMapping("/addMaintenance")
    public JsonResult addMaintenance(@RequestBody Maintenance maintenance, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String account = user.getAccount();
        String name = maintenance.getName();
        String department = maintenance.getDepartment();
        maintenance.setAccount(account);
        Boolean flag = maintenanceService.addMaintenance(maintenance);
        if (flag) {
            return new JsonResult(JsonResult.SUCCESS, null, "添加成功");
        } else {
            return new JsonResult(JsonResult.FALL, null, "添加失败,"+department+"下"+name+"已存在");
        }
    }

    /**
     * 查询当前用户所属二级学院
     * @param session
     * @return
     */
    @RequestMapping("/selectDepartment")
    public JsonResult selectDepartment(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String res = user.getDeptName();
        return new JsonResult(JsonResult.SUCCESS, res, "获取成功");
    }

    /**
     * 查询设备保养信息
     * @param maintenanceParam
     * @return
     */
    @RequestMapping("/selectMaintenance")
    public Object selectMaintenance(MaintenanceParam maintenanceParam) {
        maintenanceParam = (MaintenanceParam) ConversionStrUtil.ConversionStr(maintenanceParam);
        MaintenanceResult trainMegResult = maintenanceService.selectMaintenance(maintenanceParam);
        System.err.println(trainMegResult);
        return new JsonTemplate("查询", trainMegResult.getResultTM(), trainMegResult.getCount()).toString();
    }

    @RequestMapping("/deleteMaintenanceById")
    public JsonResult deleteMaintenanceById(@RequestBody String id) {
        Boolean flag = maintenanceService.deleteMaintenanceById(id);
        if (flag) {
            return new JsonResult(JsonResult.SUCCESS, null, "删除成功");
        } else {
            return new JsonResult(JsonResult.FALL, null, "没有数据被删除");
        }
    }

    @RequestMapping("/updateMaintenanceById")
    public JsonResult updateMaintenanceById(@RequestBody Maintenance maintenance) {
        Boolean flag = maintenanceService.updateMaintenanceById(maintenance);
        if (flag) {
            return new JsonResult(JsonResult.SUCCESS, null, "修改成功");
        } else {
            return new JsonResult(JsonResult.FALL, null, "没有数据被修改");
        }
    }
}
