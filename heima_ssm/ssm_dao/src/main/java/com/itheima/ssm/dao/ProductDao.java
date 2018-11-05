package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    /**
     * 根据id查询单个产品
     *
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id) throws Exception;

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 保存新增产品
     *
     * @param product
     */
    @Insert("insert into " +
            "product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
