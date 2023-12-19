package com203j11.example.book_store.jwt;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class JWTcreator
{
    public  String creatJWT(String uid ,String email)
    {
        try {
            JwtGenerator generator = new JwtGenerator();

            Map<String, String> claims = new HashMap<>();
            
            claims.put("uid", uid);
            claims.put("sub", "pawel.spychalski");
            claims.put("email", email);
            claims.put("aud", "*");
            claims.put("action", "read");

            String token = generator.generateJwt(claims);
           return token;
        } catch (Exception e) {
            //e.printStackTrace();
            return "";
        }
    }
}