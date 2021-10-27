package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zero
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        // 参数pageNum是当前页码值，参数pageSize代表是每页显示条数
        // 这个PageHelper一定要写在查询语句上面，中间不要有其他语句
        PageHelper.startPage(page, size);
        List<Orders> re = ordersDao.findAll();
        return re;
    }

    /**
     * 根据id查询产品
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
