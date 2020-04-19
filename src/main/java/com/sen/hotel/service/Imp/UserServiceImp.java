package com.sen.hotel.service.Imp;

import com.sen.hotel.dao.UserDao;
import com.sen.hotel.entity.User;
import com.sen.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImp implements UserService {

    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    //登录
    public User login(String account, String password) {
        Map<String,Object> map = new HashMap<String, Object>();
        if(account.contains("@")){
            map.put("email",account);
        }else {
            map.put("phone",account);
        }
        User user = userDao.selectByPhoneAndEmail(map);
        if(user != null && user.getPassword().equals(password)){
            return user;  //登录成功
        }
        return null; //登录失败
    }

    //注册
    public boolean register(User user) {
        return userDao.insert(user);
    }

    //注册检查
    public boolean registerCheck(String account) {
        Map<String,Object> map = new HashMap<String, Object>();
        if(account.contains("@")){
            map.put("email",account);
        }else {
            map.put("phone",account);
        }
        User user = userDao.selectByPhoneAndEmail(map);
        return user == null;  //user == null，用户不存在，可以注册
    }

    //修改
    public boolean updateById(User user){
        return userDao.update(user);
    }

    //根据ID查询用户
    public User selectById(int id) {
        return userDao.selectById(id);
    }

    //根据手机号查询用户
    public User selectByPhone(String phone) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("phone",phone);
        return userDao.selectByPhoneAndEmail(map);
    }

    //修改密码
    public boolean changePassword(User user) {
        return userDao.updatePassword(user);
    }
}
