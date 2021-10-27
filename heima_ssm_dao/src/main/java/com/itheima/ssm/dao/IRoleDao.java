package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Zero
 */
public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    @Select("select * from role ")
    List<Role> findAll() throws Exception;

    /**
     * 根据userid取查询对应其所有角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId}) ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId) throws Exception;

    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc}) ")
    void save(Role role) throws Exception;

    @Select("select * from role where id = #{id} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String id) throws Exception;

    /**
     * 查询当前角色未拥有的所有权限信息
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId}) ")
    List<Permission> findOtherPermissions(String roleId);

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId, permissionId) values(#{roleId}, #{permissionId}) ")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    /**
     * 根据roleId删除角色
     * @param roleId
     * @throws Exception
     */
    @Delete("delete from role where id = #{roleId} ")
    void deleteRoleById(String roleId) throws Exception;

    /**
     * 根据roleId中users_role中间表中删除关联信息
     * @param roleId
     * @throws Exception
     */
    @Delete("delete from users_role where roleId = #{roleId} ")
    void deleteFromUsers_RoleByRoleId(String roleId) throws Exception;

    /**
     * 根据roleId从role_permission中间表中删除关联信息
     * @param roleId
     * @throws Exception
     */
    @Delete("delete from role_permission where roleId = #{roleId} ")
    void deleteFromRole_PermissionByRoleId(String roleId) throws Exception;
}
