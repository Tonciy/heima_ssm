package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zero
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
             userInfo= userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority());
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getStatus() == 0? false: true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    // 作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role: roles){
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserInfo> findAll(Integer page, Integer size) throws Exception{
        // 开启分页
        // 参数pageNum是当前页码值，参数pageSize代表是每页显示条数
        // 这个PageHelper一定要写在查询语句上面，中间不要有其他语句
        PageHelper.startPage(page, size);
        return userDao.findAll();
    }

    /**
     * 保存用户
     * @param userInfo
     * @throws Exception
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    /**
     * 查询当前用户可以添加的所有角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    /**
     * 为用户添加角色
     * @param userId
     * @param roleIds
     * @throws Exception
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }

    /**
     * 通过id删除角色信息
     * @param userId
     * @throws Exception
     */
    @Override
    public void deleteUser(String userId) throws Exception {
        // 先删除users_role中间表中此用户关联角色的记录
        userDao.deleteFromUsers_roleByUserId(userId);
        // 再删除主表users中此角色信息
        userDao.deleteUser(userId);
    }
}
