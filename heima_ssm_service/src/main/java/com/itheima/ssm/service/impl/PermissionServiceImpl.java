package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zero
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有角色信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    /**
     * 保存权限信息
     * @param permission
     * @throws Exception
     */
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    /**
     * 通过id查询权限信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    /**
     * 通过permissionId删除权限信息
     * @param permissionId
     * @throws Exception
     */
    @Override
    public void deletePermission(String permissionId) throws Exception {
        // 先从role_permission从表中删除此permission关联的信息
        permissionDao.deleteFromRole_PermissionByPermissionId(permissionId);
        // 再从permission主表中删除此权限信息
        permissionDao.deletePermission(permissionId);
    }
}
