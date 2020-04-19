package com.sen.hotel.controller;

import com.sen.hotel.entity.User;
import com.sen.hotel.utils.ResponseResult;
import com.sen.hotel.service.UserService;
import com.sen.hotel.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("userController")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    ResponseResult responseResult = new ResponseResult();

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseResult login(String account, String password, boolean remember) {
        User user = userService.login(account, password);
        if (user == null) {
            return new ResponseResult().failure();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (remember) {
            map.put("account",account);
            map.put("password",password);
        }
        String token = JwtUtil.createToken(user.getId(), user.getNickName());
        map.put("token", token);
        map.put("id", user.getId());
        return responseResult.success(map);
    }

    //注册
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseResult register(User user) {
        boolean flag = userService.register(user);
        if (flag) {
            return responseResult.success();
        } else {
            return responseResult.failure("注册失败");
        }
    }

    //检查用户名是否存在
    @RequestMapping(value = "register", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseResult register(String account) {
        boolean flag = userService.registerCheck(account); //flag==true,可以注册
        if (flag) {
            return responseResult.success(); //可以注册
        } else {
            return responseResult.failure(); // 不可以注册
        }
    }

    //根据ID查询用户
    @RequestMapping(value = "user", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseResult getUser(int id) {
        User user = userService.selectById(id);
        if (user != null) {
            return responseResult.success(user);
        } else {
            return responseResult.failure("用户不存在");
        }
    }

    //根据手机号查询用户
    @RequestMapping(value = "userByPhone", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseResult getUserByPhone(String phone) {
        User user = userService.selectByPhone(phone);
        if (user != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",user.getId());
            map.put("nickName",user.getNickName());
            return responseResult.success(map);
        } else {
            return responseResult.failure("用户不存在");
        }
    }

    //修改用户信息
    @RequestMapping(value = "user", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseResult updateUser(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        if (flag) {
            return responseResult.success();
        } else {
            return responseResult.failure("修改失败");
        }
    }

    @RequestMapping(value = "change_password", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseResult changePassword(int id, String oldPass,String newPass) {
        User user = userService.selectById(id);

        if (user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            if (userService.changePassword(user)) {
                return responseResult.success("修改成功");
            }
        }
        return responseResult.failure("旧密码输入有误");
    }

    @RequestMapping(value = "forget_password", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseResult forgetPassword(Integer id, String password) {
        System.out.println(id+"~~~~"+password);
        User user = userService.selectById(id);
        if (user != null) {
            user.setPassword(password);
            if (userService.changePassword(user)) {
                return responseResult.success();
            }
        }
        return responseResult.failure("找回失败");
    }
}
