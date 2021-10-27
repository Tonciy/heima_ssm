package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static javafx.beans.binding.Bindings.select;

/**
 * @author Zero
 */
public interface IOrdersDao {

    /**
     * 查询所有账单
     * @return
     * @throws Exception
     */
    @Select("select * from orders ")
    // 对实体类属性名称和表的列名进行映射
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", one = @One(select = "com.itheima.ssm.dao.IProductDao.findById"))

    })
    List<Orders> findAll() throws Exception;

    /**
     * 根据id查询账单
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id = #{ordersId} ")
    // 对实体类属性名称和表的列名进行映射
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId) throws Exception;
}
