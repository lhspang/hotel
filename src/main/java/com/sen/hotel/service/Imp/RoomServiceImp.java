package com.sen.hotel.service.Imp;

import com.sen.hotel.dao.RoomDao;
import com.sen.hotel.dao.RoomTypeDao;
import com.sen.hotel.entity.Room;
import com.sen.hotel.entity.RoomType;
import com.sen.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("roomService")
public class RoomServiceImp implements RoomService {
    @Qualifier("roomTypeDao")
    @Autowired
    private RoomTypeDao roomTypeDao;

    @Qualifier("roomDao")
    @Autowired
    private RoomDao roomDao;

    @Override
    public boolean updateRoom(Room room) {
        return roomDao.updateRoom(room);
    }

    @Override
    public List<RoomType> selectRoomType() {
        return roomTypeDao.selectAll();
    }

    @Override
    public RoomType selectBytId(int tId) {
        return roomTypeDao.selectBytId(tId);
    }

    @Override
    public List<Room> selectByEndTime(Date endTime) {
        return roomDao.selectByEndTime(endTime);
    }
}
