package com.sen.hotel.service.Imp;

import com.sen.hotel.dao.OrderDao;
import com.sen.hotel.dao.OrderItemDao;
import com.sen.hotel.dao.RoomDao;
import com.sen.hotel.entity.Order;
import com.sen.hotel.entity.OrderItem;
import com.sen.hotel.entity.Room;
import com.sen.hotel.entity.RoomType;
import com.sen.hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("orderService")
public class OrderServiceImp implements OrderService {
    @Qualifier("orderDao")
    @Autowired
    private OrderDao orderDao;
    @Qualifier("orderItemDao")
    @Autowired
    private OrderItemDao orderItemDao;
    @Qualifier("roomDao")
    @Autowired
    private RoomDao roomDao;

    @Override
    public boolean add(Order order) {
        boolean flag = false;
        Date date = new Date();
        order.setoTime(date);

        flag = orderDao.insert(order);
        List<Integer> rIds = order.getrTds();
        OrderItem orderItem = new OrderItem(0, null, 0);
        Room room = new Room(0,null,0,null);

        for (Integer rId : rIds) {
            orderItem.setoId(order.getoId());
            orderItem.setrId(rId);
            flag = orderItemDao.insert(orderItem);

            room.setrId(rId);
            room.setEndTime(order.getOutDate());
            roomDao.updateRoom(room);

        }
        return flag;
    }

    @Override
    public Map<String, Object> selectByoId(String oId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("order",orderDao.selectByoId(oId));

        List<OrderItem> orderItems = orderItemDao.selectByoId(oId);
        RoomType roomType = roomDao.selectByrId(orderItems.get(0).getrId()).getType();

        map.put("orderItems",orderItems);
        map.put("roomType",roomType);
        return map;
    }

    @Override
    public Map<String, Object> selectById(int id) {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Order> orders = orderDao.selectById(id);
        map.put("orders",orders);

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        List<RoomType> roomTypes = new ArrayList<RoomType>();
        for (Order order : orders) {
            orderItems = orderItemDao.selectByoId(order.getoId());
            roomTypes.add(roomDao.selectByrId(orderItems.get(0).getrId()).getType());
            orderItems.clear();
        }

        map.put("roomTypes",roomTypes);
        return map;
    }
}
