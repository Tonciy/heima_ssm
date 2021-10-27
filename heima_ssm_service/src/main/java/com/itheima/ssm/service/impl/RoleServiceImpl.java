package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zero
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
         roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    /**
     * 查询当前角色所为拥有的所有权限信息
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }

    @Override
    public void deleteRole(String roleId) throws Exception {
        // 1. 从users_role表中删除
        roleDao.deleteFromUsers_RoleByRoleId(roleId);
        // 2. 从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        // 3. 从role表中删除
        roleDao.deleteRoleById(roleId);
    }
}
