package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

public interface OrderService {
    /**
     * 查询所有订单
     *
     * @return
     */
    List<Orders> findAll(int page, int size) throws Exception;

    /**
     * 根据订单id查询订单详情
     *
     * @param ordersId
     * @return
     */
    Orders findById(String ordersId) throws Exception;
}
