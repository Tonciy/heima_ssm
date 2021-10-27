package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

/**
 * @author Zero
 */
public interface IPermissionService {

    /**
     * 查询所有权限信息
     * @return
     * @throws Exception
     */
    List<Permission> findAll(Integer page, Integer size) throws Exception;

    /**
     * 保存权限信息
     * @param permission
     * @throws Exception
     */
    void save(Permission permission) throws Exception;

    /**
     * 通过id查询权限信息
     * @param id
     * @return
     * @throws Exception
     */
    Permission findById(String id) throws Exception;

    /**
     * 通过permissionId删除权限信息
     * @param permissionId
     * @throws Exception
     */
    void deletePermission(String permissionId) throws Exception;
}
