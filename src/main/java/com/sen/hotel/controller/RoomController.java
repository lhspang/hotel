package com.sen.hotel.controller;

import com.sen.hotel.entity.Room;
import com.sen.hotel.entity.RoomType;
import com.sen.hotel.service.RoomService;
import com.sen.hotel.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController("roomTypeController")
@RequestMapping("api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    ResponseResult responseResult = new ResponseResult();

    @RequestMapping(value = "all",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseResult getAllRoom(String inDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date in = simpleDateFormat.parse(inDate);

        List<Room> rooms = roomService.selectByEndTime(in);
        List<RoomType> roomTypes = new ArrayList<>();

        for(Room room:rooms){
            if(!roomTypes.contains(room.getType())){
                roomTypes.add(room.getType());
            }
            room.setType(null);
        }
        Map<Integer,List<Room>> groupRoom = rooms.stream().collect(Collectors.groupingBy(Room::gettId));

        Map<String,Object> map = new HashMap<>();
        map.put("roomType",roomTypes);
        map.put("rooms",groupRoom);
        return responseResult.success(map);
    }

}
