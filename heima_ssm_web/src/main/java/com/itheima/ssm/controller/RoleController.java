package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Zero
 */

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 删除当前角色
     * @return
     */
    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name = "id", required = true) String roleId) throws Exception{
        roleService.deleteRole(roleId);
        return "redirect:findAll.do";
    }
    /**
     * 为当前用户添加权限
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId,@RequestParam(name = "ids", required = true
    ) String[] permissionIds) throws Exception{
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
    /**
     * 查询当前角色信息以及未拥有的所有权限信息
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception{
        ModelAndView mav = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissionList = roleService.findOtherPermissions(roleId);
        mav.addObject("role", role);
        mav.addObject("permissionList", permissionList);
        mav.setViewName("role-permission-add");
        return mav;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception{
        ModelAndView mav = new ModelAndView();
        Role role = roleService.findById(id);
        mav.addObject("role", role);
        mav.setViewName("role-show");
        return mav;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }
    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<Role> roleList = roleService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(roleList);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("role-list");
        return mav;
    }
}
