package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Zero
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 通过id删除用户
     * @return
     */
    @RequestMapping("/deleteUser.do")
    public String deleteUser(@RequestParam(name = "id", required = true) String userId) throws Exception{
        userService.deleteUser(userId);
        return "redirect:findAll.do";
    }
    /**
     * 用户添加角色
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    @PreAuthorize("authentication.principal.username == 'tom'")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true
    ) String[] roleIds) throws Exception{
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
    /**
     * 查询当前用户信息和用户可以添加的所有角色信息
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) throws Exception{
        ModelAndView mav = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> roleList = userService.findOtherRoles(userId);
        mav.addObject("user", userInfo);
        mav.addObject("roleList", roleList);
        mav.setViewName("user-role-add");
        return mav;
    }

    /**
     * 根据用户id查询对应用户信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mav = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mav.addObject("user", userInfo);
        mav.setViewName("user-show1");
        System.out.println(userInfo.toString());
        return mav;
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // spel表达式
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(userList);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("user-list");
        return mav;
    }


    /**
     * 保存用户
     * @return
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }
}
