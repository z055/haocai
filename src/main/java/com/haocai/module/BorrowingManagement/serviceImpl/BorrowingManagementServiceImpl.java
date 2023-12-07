package com.haocai.module.BorrowingManagement.serviceImpl;

import com.haocai.module.BorrowingManagement.dao.BorrowingManagementDao;
import com.haocai.module.BorrowingManagement.service.BorrowingManagementService;
import com.haocai.module.BorrowingManagement.vo.BorrowingManagementVo;
import com.haocai.module.BorrowingManagement.vo.param.BorroParam;
import com.haocai.module.BorrowingManagement.vo.param.UpParam;
import com.haocai.module.BorrowingManagement.vo.resulet.BorResulet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@Transactional(propagation= Propagation.REQUIRED)
@Service
public class BorrowingManagementServiceImpl implements BorrowingManagementService {
@Autowired
private BorrowingManagementDao borrowingManagementDao;
    /**
     *
     * @return
     * 获取耗材库所有耗材信息
     */
    @Override
    public List<BorrowingManagementVo> getAllConsumables() {
        return borrowingManagementDao.getAllConsumables();
/*        List<BorrowingManagementVo> allConsumables = borrowingManagementDao.getAllConsumables();
        List<BorrowingManagementVo> list = new ArrayList<BorrowingManagementVo>();
        for (BorrowingManagementVo borrowingManagementVo :
                allConsumables) {
            String type = borrowingManagementVo.getType();
            if (type != null) {//耗材类别:酒店食品1、金属材料2、电子电器3、建筑材料4、服装服饰5、文化绘画6、工具仪表7、化工药品8、气体类9、维修保养10、其他11
                if (type.equals("1")) {
                    borrowingManagementVo.setType("酒店食品");
                } else if (type.equals("2")) {
                    borrowingManagementVo.setType("金属材料");
                } else if (type.equals("3")) {
                    borrowingManagementVo.setType("电子电器");
                } else if (type.equals("4")) {
                    borrowingManagementVo.setType("建筑材料");
                } else if (type.equals("5")) {
                    borrowingManagementVo.setType("服装服饰");
                } else if (type.equals("6")) {
                    borrowingManagementVo.setType("文化绘画");
                } else if (type.equals("7")) {
                    borrowingManagementVo.setType("工具仪表");
                } else if (type.equals("8")) {
                    borrowingManagementVo.setType("化工药品");
                } else if (type.equals("9")) {
                    borrowingManagementVo.setType("气体类");
                } else if (type.equals("10")) {
                    borrowingManagementVo.setType("维修保养");
                } else if (type.equals("11")) {
                    borrowingManagementVo.setType("其他");
                }
            }
            System.out.println("!!!!!!!!!!!!==>" + borrowingManagementVo);
        }

        return allConsumables;*/
    }
    /**
     * @return
     * 查询数据
     */
    @Override
    public BorResulet searchQuery(BorroParam borroParam) {
        //分页 页数处理
        borroParam.setPage((borroParam.getPage()-1)*borroParam.getLimit());
        //把数据封装
        BorResulet borResulet=new BorResulet();
        List<BorrowingManagementVo> list=borrowingManagementDao.searchQuery(borroParam);
//        List<BorrowingManagementVo> aList = new ArrayList<BorrowingManagementVo>();
        for (BorrowingManagementVo borrowingManagementVo :
                list) {
            String type = borrowingManagementVo.getType();
            String attribute = borrowingManagementVo.getAttribute();
            if (type != null) {//耗材类别:酒店食品1、金属材料2、电子电器3、建筑材料4、服装服饰5、文化绘画6、工具仪表7、化工药品8、气体类9、维修保养10、其他11
                if (type.equals("1")) {
                    borrowingManagementVo.setType("酒店食品");
                } else if (type.equals("2")) {
                    borrowingManagementVo.setType("金属材料");
                } else if (type.equals("3")) {
                    borrowingManagementVo.setType("电子电器");
                } else if (type.equals("4")) {
                    borrowingManagementVo.setType("建筑材料");
                } else if (type.equals("5")) {
                    borrowingManagementVo.setType("服装服饰");
                } else if (type.equals("6")) {
                    borrowingManagementVo.setType("文化绘画");
                } else if (type.equals("7")) {
                    borrowingManagementVo.setType("工具仪表");
                } else if (type.equals("8")) {
                    borrowingManagementVo.setType("化工药品");
                } else if (type.equals("9")) {
                    borrowingManagementVo.setType("气体类");
                } else if (type.equals("10")) {
                    borrowingManagementVo.setType("维修保养");
                } else if (type.equals("11")) {
                    borrowingManagementVo.setType("其他");
                }
            }
            if (attribute != null) {
                if (attribute.equals("1")) {
                    borrowingManagementVo.setAttribute("低值易耗品");
                } else if (attribute.equals("2")) {
                    borrowingManagementVo.setAttribute("低值耐用品");
                }
            }
        }
        borResulet.setList(list);
        borResulet.setCount(borrowingManagementDao.getCount(borroParam));
        return borResulet;
    }
    /**
     *
     * @return
     * 批量修改借用状态
     */
    @Override
    public int updateStatus(UpParam upParam) {
        return borrowingManagementDao.updateStatus(upParam);
    }

}
