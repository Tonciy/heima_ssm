package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Zero
 */
public interface IUserService extends UserDetailsService {
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAll(Integer page, Integer size) throws Exception;

    /**
     * 保存用户
     * @param userInfo
     * @throws Exception
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据用户id查询用户具体信息--包括角色，以及权限
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;

    /**
     * 查询当前用户所可以添加的所有角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    List<Role> findOtherRoles(String userId) throws Exception;

    /**
     * 为用户添加角色
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    void addRoleToUser(String userId, String[] roleIds) throws Exception;

    /**
     * 通过id删除角色信息
     * @param userId
     * @throws Exception
     */
    void deleteUser(String userId) throws Exception;
}
