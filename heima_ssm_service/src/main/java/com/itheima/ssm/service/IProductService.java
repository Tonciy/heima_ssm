package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * @author Zero
 */
public interface IProductService {

//    查询所有的产品信息
    public List<Product> findAll(Integer page, Integer size) throws Exception;

//    保留产品
    void save(Product product) throws Exception;
}
