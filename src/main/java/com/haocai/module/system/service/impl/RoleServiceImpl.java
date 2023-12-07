package com.haocai.module.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.haocai.module.system.dao.RoleMapper;
import com.haocai.module.system.service.RoleService;
import com.haocai.module.system.vo.Role;
import com.haocai.module.system.vo.ZTreeNode;
import com.haocai.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取角色名（带缓存）
     * @param roleIds
     * @return
     */
    @Cacheable(value = {"roleNames"},key = "targetClass.getName() + '.' + methodName + '.' + #roleIds")
    public String getRoleName(String roleIds){
        if(ValidateUtil.isEmpty(roleIds)){
            return "";
        }
        Long[] roles = Convert.toLongArray(roleIds);
        StringBuilder stringBuilder = new StringBuilder();
        for(Long role : roles){
            Role roleObj = roleMapper.selectById(role);
            if(ValidateUtil.isNotEmpty(roleObj) && ValidateUtil.isNotEmpty(roleObj.getName())){
                stringBuilder.append(roleObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(stringBuilder.toString(),",");
    }

    @Override
    public int queryAllRole(String condition) {
        return roleMapper.queryAllCount(condition);
    }

    @Override
    public List<Role> findRole(@RequestParam("page") String page, @RequestParam("limit") String limit, @RequestParam("condition") String condition) {
        int p = new Integer(page),
        l = new Integer(limit);
        int startPage = (p-1)*l;

        return roleMapper.findRole(startPage, l, condition);
    }

    @Override
    public int addRole(Role role) {
        if (roleMapper.findName(role.getName())!=null) {
            return -1;
        }
        if (role.getDescription()==null || role.getDescription()=="") {
            role.setDescription(role.getName());
        }
        return roleMapper.addRole(role);
    }

    @Override
    public int deleteRole(String name) {
        return roleMapper.deleteRole(name);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public List<ZTreeNode> getRoleToTree(String roleIds) {
        String [] roleId = roleIds.split(",");
        List<ZTreeNode> role = roleMapper.getRoleToTree();
        for (int i = 0; i < roleId.length; i++) {
            for (ZTreeNode tempNode : role) {
                String tempRoleId = tempNode.getId().toString();
                if(roleId[i].equals(tempRoleId)){
                    tempNode.setChecked(true);
                }
            }
        }
        return role;
    }
    @Override
    public List<Role> getRole() {
        return roleMapper.getRole();
    }


}
