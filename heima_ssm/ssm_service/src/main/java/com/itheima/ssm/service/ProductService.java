package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Product> findAll(int pageNum, int pageSize) throws Exception;

    /**
     * 保存新增产品
     */
    void save(Product product) throws Exception;
}
