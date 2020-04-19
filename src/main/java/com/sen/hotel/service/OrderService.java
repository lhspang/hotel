package com.sen.hotel.service;

import com.sen.hotel.entity.Order;

import java.util.Map;

public interface OrderService {

    boolean add(Order order);

    Map<String,Object> selectByoId(String oId);

    Map<String, Object> selectById(int id);
}
