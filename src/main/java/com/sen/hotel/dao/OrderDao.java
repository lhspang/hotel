package com.sen.hotel.dao;

import com.sen.hotel.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public interface OrderDao {

    @Insert("insert into tb_order(oId,totalPrice,totalNum,oTime,inDate,outDate,uname,phone,idcardnum,id) " +
            "values(#{oId},#{totalPrice},#{totalNum},#{oTime},#{inDate},#{outDate},#{uname},#{phone},#{idcardnum},#{id})")
    boolean insert(Order order);

    @Select("select * from tb_order where oId = #{oId}")
    Order selectByoId(String oId);

    @Select("select * from tb_order where id = #{id} order by oTime desc")
    List<Order> selectById(int id);

}
