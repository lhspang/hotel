package com.sen.hotel.service;

import com.sen.hotel.entity.Room;
import com.sen.hotel.entity.RoomType;

import java.util.Date;
import java.util.List;

public interface RoomService {

    boolean updateRoom(Room room);

    List<RoomType> selectRoomType();

    RoomType selectBytId(int tId);

    List<Room> selectByEndTime(Date endTime);
}
