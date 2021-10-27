package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Zero
 */
public interface IProductDao {

    /**
     * 根据id值查询产品
     * @return
     * @throws Exception
     */
    @Select("select * from product where id = #{id} ")
    public Product findById(String id) throws Exception;

    // 查询所有的产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    /**
     * 保存产品信息
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
