package com.haocai.module.trainmegKeep.controller;

import com.haocai.module.sbModule.service.SbService;
import com.haocai.module.sbModule.vo.param.SbParam;
import com.haocai.module.system.vo.User;
import com.haocai.module.trainmegKeep.service.TrainMegService;
import com.haocai.module.trainmegKeep.vo.TrainMeg;
import com.haocai.module.trainmegKeep.vo.param.TrainMegParam;
import com.haocai.module.trainmegKeep.vo.result.TrainMegResult;
import com.haocai.utils.ConversionStrUtil;
import com.haocai.utils.JsonResult;
import com.haocai.utils.JsonTemplate;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/trainMeg")
@ResponseBody
public class TrainMegController {
    @Autowired
    private TrainMegService trainMegService;

    /**
     * 添加实训、大赛维护数据
     * @param trainMeg
     * @return
     */
    @RequestMapping("/addTrainMeg")
    public JsonResult addTrainMsg(@RequestBody TrainMeg trainMeg, HttpSession session) {
        System.err.println("trainMeg:" + trainMeg);
        User user = (User) session.getAttribute("user");
        String account = user.getAccount();
        trainMeg.setAccount(account);
        Boolean notExit = trainMegService.selectDepTrainMegByDep(trainMeg);
        if (!notExit) {//有相同的不可重复添加
            return new JsonResult(JsonResult.FALL, null, "该二级学院已存在相同的实训/大赛名称，无法重复添加");
        }
        Boolean flag = trainMegService.addTrainMeg(trainMeg);
        if (flag) {
            return new JsonResult(JsonResult.SUCCESS, null, "添加成功");
        } else {
            return new JsonResult(JsonResult.FALL, null, "添加失败");
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
     * 查询实训、大赛维护信息
     * @param trainMegParam
     * @return
     */
    @RequestMapping("/selectTrainMsg")
    public Object selectTrainMsg(TrainMegParam trainMegParam) {
        trainMegParam = (TrainMegParam) ConversionStrUtil.ConversionStr(trainMegParam);
        TrainMegResult trainMegResult = trainMegService.selectTrainMeg(trainMegParam);
        System.err.println(trainMegResult);
        return new JsonTemplate("查询", trainMegResult.getResultTM(), trainMegResult.getCount()).toString();
    }

    @RequestMapping("/deleteTrainMsgById")
    public JsonResult deleteTrainMsgById(@RequestBody String id) {
        Boolean flag = trainMegService.deleteTrainMegById(id);
        if (flag) {
            return new JsonResult(JsonResult.SUCCESS, null, "删除成功");
        } else {
            return new JsonResult(JsonResult.FALL, null, "没有数据被删除");
        }
    }

    @RequestMapping("/updateTrainMegById")
    public JsonResult updateTrainMegById(@RequestBody TrainMeg trainMeg) {
        System.err.println(trainMeg);
        Boolean notExit = trainMegService.selectDepTrainMegByDep(trainMeg);
        if (!notExit) {//有相同的不可重复添加
            return new JsonResult(JsonResult.FALL, null, "该二级学院已存在相同的实训/大赛名称，无法修改为已存在的");
        }
        Boolean flag = trainMegService.updateTrainMegById(trainMeg);
        if (flag) {
            return new JsonResult(JsonResult.SUCCESS, null, "修改成功");
        } else {
            return new JsonResult(JsonResult.FALL, null, "没有数据被修改");
        }
    }
}
