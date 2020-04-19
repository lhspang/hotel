package com.sen.hotel.dao;

import com.sen.hotel.entity.Room;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("roomDao")
public interface RoomDao {

    @Update("update tb_room set endTime=#{endTime} where rId=#{rId}")
    boolean updateRoom(Room room);

    //根据房号查询房间信息
    @Select("select * from tb_room where rId=#{rId}")
    @Results(id = "room",value = {@Result(property = "rId", column = "rId", id = true),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "tId", column = "tId"),
            @Result(property = "type", column = "tId",
                    one = @One(select = "com.sen.hotel.dao.RoomTypeDao.selectBytId", fetchType = FetchType.EAGER))
    })
    Room selectByrId(int rId);

    //根据房型查询所有房间信息
    @Select("select * from tb_room where tId=#{tId}")
    List<Room> selectBytid(int tId);

    @Select("select * from tb_room where endTime <= #{endTime} order by tId")
    @ResultMap("room")
    List<Room> selectByEndTime(Date endTime);
}
