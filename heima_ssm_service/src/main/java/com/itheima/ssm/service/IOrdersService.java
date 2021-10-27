package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;


import java.util.List;

/**
 * @author Zero
 */
public interface IOrdersService {

    List<Orders> findAll(int page, int size) throws  Exception;

    /**
     * 根据id查询产品
     * @param ordersId
     * @return
     * @throws Exception
     */
    Orders findById(String ordersId) throws Exception;
}
