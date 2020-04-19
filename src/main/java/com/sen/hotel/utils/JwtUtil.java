package com.sen.hotel.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //有效期2天
    private static final long EXPIRE_TIME = 3600 * 1000 * 24 * 2;
    private static final String SECRET = "zCaKtBnS3eGOHADd";

    public static String createToken(int id, String nickName) {
        String token = "";
        try {
            //签发时间
            Date currentTime = new Date();
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            token =  JWT.create()
                    .withIssuer("sen")
                    .withHeader(header)
                    .withClaim("id", id)
                    .withClaim("nickName", nickName)
                    .withExpiresAt(new Date(currentTime.getTime() + EXPIRE_TIME))
                    .withIssuedAt(currentTime)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static Map<String,Claim> verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("sen").build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
