package com.sen.hotel.controller;

import com.sen.hotel.entity.Order;
import com.sen.hotel.service.OrderService;
import com.sen.hotel.service.RoomService;
import com.sen.hotel.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController("orderController")
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomService roomService;
    ResponseResult responseResult = new ResponseResult();

    @RequestMapping(value = "order", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseResult addOrder(Order order) {
        boolean flag = orderService.add(order);
        if (flag) {
            return responseResult.success();
        } else {
            return responseResult.failure("下单失败");
        }
    }

    @RequestMapping(value = "order", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseResult selectOrderByoId(String oId) {
        Map<String,Object> map = orderService.selectByoId(oId);
        if (map != null) {
            return responseResult.success(map);
        } else {
            return responseResult.failure("未找到订单");
        }
    }

    @RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseResult selectOrderById(int id) {
        Map<String,Object> map = orderService.selectById(id);
        if(map!= null){
            return responseResult.success(map);
        }else {
            return responseResult.failure("未找到订单");
        }
    }
}
