package com203j11.example.book_store.jwt;

import java.security.InvalidParameterException;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Hello world!
 *
 */
public class JWTV 
{
    public  String validate( String tkn )
    {

        final JwtValidator validator = new JwtValidator();

        try {
            DecodedJWT token = validator.validate(tkn);
            System.out.println( token );
            String email = JWT.decode(tkn).getClaim("email").asString();
           String uid = JWT.decode(tkn).getClaim("email").asString();
            return "email = "+email + "  uid = " + uid + " message = " + "Valid token";
        } catch (InvalidParameterException e) {
            System.out.println( "Jwt is invalid" );
            e.printStackTrace();


            
            String email = JWT.decode(tkn).getClaim("email").asString();
           String uid = JWT.decode(tkn).getClaim("email").asString();

            return "email = "+email + "  uid = " + uid + " message = " + e.getMessage();
        }

    }
}