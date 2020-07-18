package com.juck.recovery.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JWTUtil {
    private JWTUtil() {
    }

    public static String genToken(String username, String password) {
        Date start = new Date();
        Date end = Date.from(LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).plusMinutes(3).atZone(ZoneId.systemDefault()).toInstant());

        return JWT.create().withAudience(username).withIssuedAt(new Date()).withExpiresAt(end)
                .sign(Algorithm.HMAC256(password));
    }
}
