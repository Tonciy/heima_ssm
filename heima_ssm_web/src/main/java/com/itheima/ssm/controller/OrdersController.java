package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author Zero
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    // 未分页查询
    //    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception{
//        List<Orders> orders = ordersService.findAll();
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("ordersList", orders);
//        mav.setViewName("orders-list");
//        return mav;
//    }

    // 带分页的查询
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN") // 只有具有了ADMIN权限的用户才可以访问此方法
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(orders);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("orders-list");
        return mav;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String ordersId) throws Exception{
        ModelAndView mav = new ModelAndView();
        Orders res = ordersService.findById(ordersId);
        mav.addObject("orders", res);
        mav.setViewName("orders-show");
        return mav;
    }
}
