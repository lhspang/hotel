package com.sen.hotel.dao;

import com.sen.hotel.entity.RoomType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roomTypeDao")
public interface RoomTypeDao {

    //根据房型id查询该房型的信息
    @Select("select * from tb_room_type where tId=#{tId}")
    RoomType selectBytId(int tId);

    //查询所有房型信息
    @Select("select * from tb_room_type")
    @Results({
            @Result(property = "tId", column = "tId", id = true),
            @Result(property = "roomList", column = "tId",
                    many = @Many(select = "com.sen.hotel.dao.RoomDao.selectBytid"))
    })
    List<RoomType> selectAll();
}
