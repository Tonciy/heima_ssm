package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Zero
 */
public interface IPermissionDao {

    /**
     * 查询所有权限信息
     * @return
     * @throws Exception
     */
    @Select("select * from permission ")
    List<Permission> findAll() throws Exception;

    /**
     * 根据角色id查询对应全部权限信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id}) ")
    List<Permission> findPermissionByRoleId(String id) throws Exception;

    /**
     * 保存权限信息
     * @param permission
     * @throws Exception
     */
    @Insert("insert into permission(permissionName, url) values(#{permissionName}, #{url}) ")
    void save(Permission permission) throws Exception;

    /**
     * 通过permissionId来查询权限信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id = #{id} ")
    Permission findById(String id) throws Exception;

    /**
     * 通过permissionId删除此权限信息
     * @param permissionId
     * @throws Exception
     */
    @Delete("delete from permission where id = #{permissionId} ")
    void deletePermission(String permissionId) throws Exception;

    /**
     * 通过permissionId在从表role_permission中删除关联信息
     * @param permissionId
     * @throws Exception
     */
    @Delete("delete from role_permission where permissionId = #{permissionId} ")
    void deleteFromRole_PermissionByPermissionId(String permissionId) throws Exception;
}
