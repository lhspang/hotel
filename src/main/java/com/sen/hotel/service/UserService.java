package com.sen.hotel.service;

import com.sen.hotel.entity.User;

public interface UserService {

    //登录
    User login(String account, String password);

    //注册
    boolean register(User user);

    //注册检查
    boolean registerCheck(String account);

    //查询用户信息
    User selectById(int id);

    //根据手机号查询用户
    User selectByPhone(String phone);

    //修改信息
    boolean updateById(User user);

    //修改密码
    boolean changePassword(User user);

}
