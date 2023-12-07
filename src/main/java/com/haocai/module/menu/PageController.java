package com.haocai.module.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/welcome")
    public String welcome1() {
        return "page/welcome";
    }

    //设备保养
    @RequestMapping("/equipmentMaintenance")
    public String equipmentMaintenance() {
        return "page/equipmentMaintenance/maintenance";
    }

    @RequestMapping("/addEquipmentMaintenance")
    public String addEquipmentMaintenance() {
        return "page/equipmentMaintenance/add/addMaintenance";
    }

    @RequestMapping("/updateEquipmentMaintenance")
    public String updateEquipmentMaintenance() {
        return "page/equipmentMaintenance/update/updateMaintenance";
    }

    //二级学院管理员  负责的 本学院下专业负责人的专业修改页面
    @RequestMapping("/userMajor")
    public String userMajor() {
        return "page/system/userMajor";
    }

    @RequestMapping("/updateUserMajor")
    public String updateUserMajor() {
        return "page/system/updateUserMajor";
    }


    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/error404")
    public String error404() {
        return "page/error/404";
    }

    @RequestMapping("/menu")
    public String menu() {
        return "page/system/menuMgr";
    }

    @RequestMapping("/userSetting")
    public String userSetting() {
        return "page/userInfo/user-setting";
    }

    @RequestMapping("/chooseRole")
    public String chooseRole() {
        return "page/userInfo/choose_role";
    }

    @RequestMapping("/changePassword")
    public String changePassword() {
        return "page/userInfo/user-password";
    }

    @RequestMapping("/userMgr")
    public String userMgr() {
        return "page/system/user";
    }

    @RequestMapping("/roleMgr")
    public String roleMgr() {
        return "page/system/roleMgr";
    }

    @RequestMapping("/role_add")
    public String role_add() {
        return "page/system/role_add";
    }

    @RequestMapping("/role_edit")
    public String role_edit() {
        return "page/system/role_edit";
    }

    @RequestMapping("/deptMgr")
    public String deptMgr() {
        return "page/system/deptMgr";
    }

    @RequestMapping("/userAdd")
    public String userAdd() {
        return "page/system/user_add";
    }

    @RequestMapping("/userEdit")
    public String userEdit() {
        return "page/system/user_edit";
    }

    @RequestMapping("/userChangeRole")
    public String userChangeRole() {
        return "page/system/user_change_role";
    }

    @RequestMapping("/menuMgr")
    public String menuMgr() {
        return "page/system/menuMgr";
    }

    @RequestMapping("/assignPermissions")
    public String assignPermissions() {
        return "page/system/assignPermissions";
    }

    //课程相关
    @RequestMapping("/courseMgr")
    public String courseMgr() {
        return "page/course/courseMgr";
    }

    @RequestMapping("/course_add")
    public String course_add() {
        return "page/course/course_add";
    }

    @RequestMapping("/course_edit")
    public String course_edit() {
        return "page/course/course_edit";
    }

    //新入库
    @RequestMapping("/inConAuto")
    public String InConAuto() {
        return "page/InCon/audit/InConAuto";
    }

    @RequestMapping("/inReviewPage")
    public String inReviewPage() {
        return "page/InCon/audit/inReviewPage";
    }

    @RequestMapping("/addInConPage")
    public String addInConPage() {
        return "page/InCon/apply/addInConPage";
    }

    @RequestMapping("/inCon")
    public String inCon() {
        return "page/InCon/InManagement/inCon";
    }

    @RequestMapping("/inSubmitPage")
    public String inSubmitPage() {
        return "page/InCon/InManagement/inSubmitPage";
    }

    @RequestMapping("/inConSubmit")
    public String outInSubmitPage() {
        return "page/InCon/apply/InConSubmit";
    }

    @RequestMapping("/addInCon")
    public String addInCon() {
        return "page/InCon/InManagement/addInCon";
    }
    //新出库

    @RequestMapping("/outConProcess")
    public String outConProcess() {
        return "page/outCon/process/outConProcess";
    }

    @RequestMapping("/outConAuto")
    public String outConAuto() {
        return "page/outCon/audit/outConAuto";
    }

    @RequestMapping("/outReviewPage")
    public String outReviewPage() {
        return "page/outCon/audit/outReviewPage";
    }

    @RequestMapping("/outCon")
    public String outCon() {
        return "page/outCon/OutManagement/OutCon";
    }

    //出库提交
    @RequestMapping("/addOutCon")
    public String addOutConPage() {
        return "page/outCon/apply/addOutConPage";
    }

    @RequestMapping("/conTypeName")
    public String conTypeName() {
        return "page/outCon/apply/conTypeName";
    }

    @RequestMapping("/outConSubmit")
    public String outConSubmitPage() {
        return "page/outCon/apply/outConSubmit";
    }

    //审核提交
    @RequestMapping("/outSubmitPage")
    public String outSubmitPage() {
        return "page/outCon/OutManagement/outSubmitPage";
    }

    // 采购相关
    @RequestMapping("/purchaseMgr")
    public String purchaseMgr() {
        return "page/purchase/purchase";
    }


    @RequestMapping("/purchaseReviewAll")
    public String purchaseReviewAll() {
        return "page/purchase/purchase_ReviewAll";
    }

    @RequestMapping("/purchaseRedit")
    public String purchaseRedit() {
        return "page/purchase/purchase_Redit";
    }

    @RequestMapping("/purchaseAdd")
    public String purchaseAdd() {
        return "page/purchase/purchase_add";
    }

    @RequestMapping("/purchaseEdit")
    public String purchaseEdit() {
        return "page/purchase/purchase_edit";
    }

    @RequestMapping("/purchaseShenhe")
    public String purchaseShenhe() {
        return "page/purchase/purchase_shenhe";
    }

    @RequestMapping("/purchaseAudit")
    public String purchaseAudit() {
        return "page/purchase/purchase_audit";
    }

    @RequestMapping("/purchaseImport")
    public String purchaseImport() {
        return "page/purchase/purchase_import";
    }

    @RequestMapping("/purchaseLogs")
    public String purchaseLogs() {
        return "page/purchase/purchase_logs";
    }

    @RequestMapping("/purchaseProcess")
    public String purchaseProcess() {
        return "page/purchase/purchase_process";
    }

    @RequestMapping("/purchaseShenImport")
    public String purchaseShenImport() {
        return "page/purchase/purchase_ShenImport";
    }


    //外借
    @RequestMapping("/management")
    public String management() {
        return "page/waijie/management";
    }

    @RequestMapping("/SbModuleSubmitPage")
    public String SbModuleSubmitPage() {
        return "page/sbModule/SbModuleSubmitPage";
    }

    @RequestMapping("/sbModuleGuanli")
    public String sbModuleGuanli() {
        return "page/sbModule/sbModuleGuanli";
    }

    @RequestMapping("/sbDetails")
    public String sbDetails() {
        return "page/sbModule/sbDetails";
    }

    @RequestMapping("/SubmitSbAdd")
    public String SubmitSbAdd() {
        return "page/sbModule/apply/SubmitSbAdd";
    }

    @RequestMapping("/addSbConPage")
    public String addSbConPage() {
        return "page/sbModule/apply/addSbConPage";
    }

    @RequestMapping("/addReturn")
    public String addReturn() {
        return "page/sbModule/apply/addReturn";
    }

    @RequestMapping("/sbStorageConsReviewPage")
    public String sbStorageConsReviewPage() {
        return "page/sbModule/backAudit/sbStorageConsReviewPage";
    }

    @RequestMapping("/sbConAuto")
    public String sbConAuto() {
        return "page/sbModule/audit/sbConAuto";
    }

    @RequestMapping("/sboutReviewPage")
    public String sbOutReviewPage() {
        return "page/sbModule/audit/sboutReviewPage";
    }

    @RequestMapping("/sbModuleShenhe")
    public String sbModuleShenhe() {
        return "page/sbModule/audit/sbModuleShenhe";
    }

    @RequestMapping("/sbBackConAuto")
    public String sbBackConAuto() {
        return "page/sbModule/backAudit/sbBackConAuto";
    }

    //目录管理
    @RequestMapping("/consumablesList")
    public String consumablesList() {
        return "page/consumableslist/consumablesList";
    }

    @RequestMapping("/consumablesListAdd")
    public String consumablesListAdd() {
        return "page/consumableslist/consumablesList_add";
    }

    @RequestMapping("/consumablesListEdit")
    public String consumablesListEdit() {
        return "page/consumableslist/consumablesList_edit";
    }

    @RequestMapping("/consumablesListModify")
    public String consumablesListModify() {
        return "page/consumableslist/consumablesList_modify";
    }

    @RequestMapping("/consumablesListTime")
    public String consumablesListTime() {
        return "page/consumableslist/consumablesList_time";
    }

    @RequestMapping("/consumablesListReview")
    public String consumablesListReview() {
        return "page/consumableslist/consumablesList_review";
    }

    @RequestMapping("/reviewPage")
    public String reviewPage() {
        return "page/consumableslist/reviewPage";
    }

    @RequestMapping("/consumablesListAuditSubmit")
    public String consumablesListAuditSubmit() {
        return "page/consumableslist/consumablesList_auditSubmit";
    }

    //课程管理
    @RequestMapping("/addCourse")
    public String addCourse() {
        return "page/course/course_add";
    }

    @RequestMapping("/editCourse")
    public String editCourse() {
        return "page/course/course_edit";
    }

    //盘点管理
    @RequestMapping("/inventory")
    public String inventory() {
        return "page/inventory/inventory";
    }

    @RequestMapping("/outInventory")
    public String chukuInventory() {
        return "page/inventory/outInventory";
    }

    @RequestMapping("/inInventory")
    public String inInventory() {
        return "page/inventory/inInventory";
    }


    //报废
    @RequestMapping("/ScrapConSubmit")
    public String ScrapConSubmit() {
        return "page/scrapCon/apply/ScrapConSubmit";
    }

    //添加
    @RequestMapping("/addScrapConPage")
    public String addScrapConPage() {
        return "page/scrapCon/apply/addScrapConPage";
    }

    //审核
    @RequestMapping("/scrapReviewPage")
    public String scrapReviewPage() {
        return "page/scrapCon/audit/scrapReviewPage";
    }

    @RequestMapping("/scrapScrapAuto")
    public String scrapScrapAuto() {
        return "page/scrapCon/audit/scrapScrapAuto";
    }

    //管理
    @RequestMapping("/ScrapCon")
    public String scrapCon() {
        return "page/scrapCon/scrapManagement/ScrapCon";
    }

    @RequestMapping("/ScrapSubmitPage")
    public String ScrapSubmitPage() {
        return "page/scrapCon/scrapManagement/ScrapSubmitPage";
    }

    //实训、大赛维护
    @RequestMapping("/trainmeg")
    public String trainmeg() {
        return "page/trainmegKeep/trainmeg";
    }

    @RequestMapping("/addTrainmeg")
    public String addTrainmeg() {
        return "page/trainmegKeep/add/addTrainmeg";
    }

    @RequestMapping("updateTrainMeg")
    public String updateTrainMeg() {
        return "page/trainmegKeep/update/updateTrainMeg";
    }
}
