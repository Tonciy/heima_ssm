package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Zero
 */
public interface IUserDao {

    /**
     * 根据用户账户查询用户信息
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users ")
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
     * @param userInfo
     */
    @Insert("insert into users(email, username, password, phoneNum, status) values(#{email}, #{username}, #{password}, #{phoneNum}, #{status}) ")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据用户id查询用户具体信息--包括权限，角色
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id  = #{id} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    /**
     * 查询当前用户可以添加的所有角色信息
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId}) ")
    List<Role> findOtherRoles(String userId);

    /**
     * 为用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role(userId, roleId) values(#{userId}, #{roleId}) ")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 通过id删除角色
     * @param userId
     * @throws Exception
     */
    @Delete("delete from users where id = #{userId} ")
    void deleteUser(String userId) throws Exception;

    @Delete("delete from users_role where userId = #{userId} ")
    void deleteFromUsers_roleByUserId(String userId) throws Exception;
}
