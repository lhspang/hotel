package com.sen.hotel.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;

import java.util.HashMap;
import java.util.Map;

public class QiniuPhoneUtil {
    public String phoneCode(String phone){
        // 设置需要操作的账号的AK和SK
        String ACCESS_KEY = "4d2ILVvnJxp_rRf9d6evACyaAIelwT2eca4DVYT8";
        String SECRET_KEY = "PiFwwD1qDE5Wfs7uJIalrUv5I0jyRUsWiDDTEr9r";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        // 实例化一个SmsManager对象
        SmsManager smsManager = new SmsManager(auth);
        String phoneCode = randomPhoneCode();

        try {
            Map<String,String> map = new HashMap<String, String>();
            map.put("code",phoneCode);
            Response response = smsManager.sendMessage("1216651306089848832",new String[]{phone},map);
            System.out.println(response);
            return phoneCode;
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String randomPhoneCode(){
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }
}
