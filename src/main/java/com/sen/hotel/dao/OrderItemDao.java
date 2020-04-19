package com.sen.hotel.dao;

import com.sen.hotel.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderItemDao")
public interface OrderItemDao {

    @Insert("insert into tb_order_item(oId,rId) values(#{oId},#{rId})")
    boolean insert(OrderItem orderItem);

    @Select("select * from tb_order_item where oId=#{oId}")
    List<OrderItem> selectByoId(String oId);
}
