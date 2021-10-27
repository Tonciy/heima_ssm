package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zero
 */
@Service
//  业务层一定要注意事务
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;


    // 查询所有产品
    @Override
    public List<Product> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

//    保存产品
    @Override
    public void save(Product product) throws Exception{
        productDao.save(product);
    }


}
