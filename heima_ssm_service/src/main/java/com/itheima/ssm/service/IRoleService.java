package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

/**
 * @author Zero
 */
public interface IRoleService {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll(Integer page, Integer size) throws Exception;

    /**
     * 保存role信息
     * @param role
     * @throws Exception
     */
    void save(Role role) throws Exception;

    /**
     * 根据id查询role信息
     * @param id
     * @return
     * @throws Exception
     */
    Role findById(String id) throws Exception;

    /**
     * 查询当前角色所未拥有的所有权限信息
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Permission> findOtherPermissions(String roleId) throws Exception;

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    /**
     * 根据roleId删除角色
     * @param roleId
     * @throws Exception
     */
    void deleteRole(String roleId) throws Exception;
}
