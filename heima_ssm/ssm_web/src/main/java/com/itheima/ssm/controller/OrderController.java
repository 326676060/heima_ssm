package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单
     *
     * @param model
     * @return
     */
    @RequestMapping("findAll")
    public String findAll(Model model, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "4") int size) throws Exception {
        List<Orders> list = orderService.findAll(page, size);
        PageInfo<Orders> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "orders-list";
    }

    ///ssm_web/orders/findById.do
    @RequestMapping("findById")
    public String findById(Model model, @RequestParam("id") String ordersId) throws Exception {
        Orders orders = orderService.findById(ordersId);
        model.addAttribute("orders",orders);
        return "orders-show";
    }
}
