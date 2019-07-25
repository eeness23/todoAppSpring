package com.enes.fullstacktodoapp.full_stack_todo_app.Security;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.enes.fullstacktodoapp.full_stack_todo_app.Security.SecurityConstants.EXPIRATION_TIME;
import static com.enes.fullstacktodoapp.full_stack_todo_app.Security.SecurityConstants.SECRET_KEY;

@Component
public class JwtFunctions {

    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date exprityTime = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",userId);
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullName());


        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exprityTime)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }
}
