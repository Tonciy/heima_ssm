package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Zero
 */
public interface ITravellerDao {

    /**
     * 根据订单id查询旅客信息  n--n
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId}) ")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
