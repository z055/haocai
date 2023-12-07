package com.haocai.module.system.service.impl;

import com.haocai.module.system.dao.DeptMapper;
import com.haocai.module.system.service.DeptService;
import com.haocai.module.system.vo.Dept;
import com.haocai.module.system.vo.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;


    @Override
    public int queryAllCount(String condition) {
        return deptMapper.queryAllCount(condition);
    }

    @Override
    public List<Dept> findDept(String page, String limit, String condition) {
        int p = new Integer(page),
                l = new Integer(limit);
        int startPage = (p-1)*l;
        return deptMapper.findDept(startPage, l, condition);
    }

    @Override
    public int deleteDept(Dept dept) {
        return deptMapper.deleteDept(dept);
    }

    @Override
    public List<Dept> getPDept() {
        return deptMapper.getPDept();
    }

    @Override
    public List<Dept> getDept() {
        return deptMapper.getDept();
    }

    @Override
    public int addDept(Dept dept) {
        String str = String.valueOf(UUID.randomUUID()).replace("-" ,"");
        dept.setDept_id(str);
        if(dept == null || dept.getDept_id() == null) {
            return 0;
        }
        return deptMapper.addDept(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateDept(dept);
    }

    @Override
    public List<ZTreeNode> getDeptToTree() {
        List<ZTreeNode> treeNode = new ArrayList<>();
        // 设置ztree树顶级节点
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setId("0");
        zTreeNode.setpId("-1");
        zTreeNode.setName("顶级");
        zTreeNode.setIcon("/images/shuzhuangtu.png");
        List<Map<String, Object>> deptTree = deptMapper.getDeptToTree();
        for (Map<String, Object> node : deptTree) {
            ZTreeNode temp = new ZTreeNode();
            temp.setId(node.get("dept_id"));
            temp.setpId(node.get("pid"));
            temp.setName(node.get("full_name").toString());
            if (Integer.parseInt(node.get("sonNum").toString()) > 0){
                temp.setIcon("/images/shuzhuangtu.png");
            }else{
                temp.setIcon("/images/zijiedian.png");
            }
            treeNode.add(temp);
        }
        treeNode.add(zTreeNode);
        return treeNode;
    }

    @Override
    public List<Dept> findDeptToSelect() {
        return deptMapper.findDeptToSelect();
    }


}
