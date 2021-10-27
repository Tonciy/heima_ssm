package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
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
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    /**
     * 通过permissionId删除权限
     * @return
     */
    @RequestMapping("/deletePermission.do")
    public String deletePermission(@RequestParam(name = "id", required = true) String permissionId) throws Exception{
        permissionService.deletePermission(permissionId);
        return "redirect:findAll.do";
    }

    /**
     * 通过id查询权限信息
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception{
        ModelAndView mav = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mav.addObject("permission", permission);
        mav.setViewName("permission-show");
        return mav;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
    /**
     * 查询所有权限信息
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(permissionList);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("permission-list");
        return mav;
    }
}
