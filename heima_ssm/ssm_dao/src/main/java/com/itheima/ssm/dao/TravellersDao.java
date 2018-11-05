package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellersDao {
    /**
     * 根据订单id查询游客
     * @param orderId
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{orderId} ) ")
    List<Traveller> findByOrdersId(String orderId) throws Exception;
}
