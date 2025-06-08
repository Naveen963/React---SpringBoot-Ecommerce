package com.ecommerce.sb_ecom.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

     private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

     @Value("${spring.app.jwtSecret}")
     private String jwtSecretKey;

     @Value("${spring.app.jwtExpirationTimeinMs}")
     private int jwtExpirationTimeinMs;

     // Get jwt from header
     public String getJwtFromHeader(HttpServletRequest request){
         String bearerToken = request.getHeader("Authorization");
         logger.debug("Authorization Header: {}",bearerToken);
         if(bearerToken!=null && bearerToken.startsWith("Bearer")){
             return bearerToken.substring(7);
         }
         return null;
     }

     // Generate jwt from username
    public String generateJwtTokenFromUsername(UserDetails userDetails){

         String username = userDetails.getUsername();

         return Jwts.builder()
                 .subject(username)
                 .issuedAt(new Date())
                 .expiration(new Date((new Date()).getTime()+jwtExpirationTimeinMs))
                 .signWith(key())
                 .compact();

    }

    //  //Getting username from JWT token
    public String generateUsernameFromToken(String token){
         return Jwts.parser()
                 .verifyWith((SecretKey) key())
                 .build()
                 .parseSignedClaims(token)
                 .getPayload()
                 .getSubject();
    }



    //Generate signing key
    public Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecretKey)
        );
    }

    //verify jwt
    public boolean ValidateJwt(String token){
         try{
             Jwts.parser()
                     .verifyWith((SecretKey) key())
                     .build()
                     .parseSignedClaims(token);
             return true;
         }
         catch (MalformedJwtException exception){
             logger.error("Invalid JWT token: {}", exception.getMessage());
         }
         catch (ExpiredJwtException exception){
             logger.error("JWT token is expired: {}", exception.getMessage());

         }
         catch (UnsupportedJwtException exception){
             logger.error("JWT token is unsupported: {}", exception.getMessage());

         }
         catch (IllegalArgumentException exception){
             logger.error("JWT claims string is empty: {}", exception.getMessage());

         }
        return  false;
    }
}
