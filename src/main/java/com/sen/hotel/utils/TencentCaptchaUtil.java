package com.sen.hotel.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.captcha.v20190722.CaptchaClient;

import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultRequest;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultResponse;

public class TencentCaptchaUtil {
    public boolean captcha(String ticket, String userIp, String randstr) {
        try {

            Credential cred = new Credential("AKIDrXTBiOnjenlVy4SSQ8aopa5dqoWyC3EA", "ytYx61hANKEtLYYJq6MYVFn7LJ5LYXHm");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("captcha.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CaptchaClient client = new CaptchaClient(cred, "ap-beijing", clientProfile);

            String params = "{\"CaptchaType\":9,\"Ticket\":" + ticket + ",\"UserIp\":" + userIp + ",\"Randstr\":" + randstr + ",\"CaptchaAppId\":2019980495,\"AppSecretKey\":\"0WvENXPQdPMhAbh9HCK53KQ**\"}";
            DescribeCaptchaResultRequest req = DescribeCaptchaResultRequest.fromJsonString(params, DescribeCaptchaResultRequest.class);

            DescribeCaptchaResultResponse resp = client.DescribeCaptchaResult(req);

            return resp.getCaptchaCode() == 1;
        } catch (TencentCloudSDKException e) {
            return false;
        }
    }
}
