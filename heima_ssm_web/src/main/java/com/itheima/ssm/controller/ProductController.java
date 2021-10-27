package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Zero
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    @RequestMapping("/save.do")
    public String  save(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")  //只有具有ADMIN(严格区分大小写，admin不行)角色的用户才可以访问 ROLE_ 为前缀，使用secured这种类型注解必须需要的
    public ModelAndView findAll(@RequestParam(defaultValue = "1", required = true, name = "page") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
}
