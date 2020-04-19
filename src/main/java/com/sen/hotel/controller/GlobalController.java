package com.sen.hotel.controller;

import com.auth0.jwt.interfaces.Claim;
import com.sen.hotel.utils.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("globalController")
@RequestMapping("api/code")
public class GlobalController {
    ResponseResult responseResult = new ResponseResult();

    @RequestMapping(value = "token",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public ResponseResult checkToken(String token){
        Map<String, Claim> map = JwtUtil.verifyToken(token);
        if(map!=null){
            return responseResult.success();
        }else {
            return responseResult.failure("token已过期");
        }
    }

    @RequestMapping(value = "phone",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public ResponseResult phoneCode(String phone){
        QiniuPhoneUtil qiniuPhoneUtil = new QiniuPhoneUtil();
        String phoneCode = qiniuPhoneUtil.phoneCode(phone);
        if(phoneCode!= null){
            return responseResult.success(phoneCode);
        }else {
            return responseResult.failure("手机号有误");
        }
    }

    @RequestMapping(value = "captcha",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public ResponseResult Captcha(String ticket,String userIp,String randstr){
        TencentCaptchaUtil tencentCaptchaUtil = new TencentCaptchaUtil();
        boolean flag = tencentCaptchaUtil.captcha(ticket,userIp,randstr);
        if(flag){
            return responseResult.success();
        }else {
            return responseResult.failure("验证码有误");
        }
    }

}
