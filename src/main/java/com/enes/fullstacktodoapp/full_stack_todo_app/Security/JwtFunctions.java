package com.enes.fullstacktodoapp.full_stack_todo_app.Security;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import io.jsonwebtoken.*;
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

    public Long getIdFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.get("id").toString());
    }

    public boolean checkTokenValid(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            System.out.println("Signature Exception");
        }catch (MalformedJwtException e){
            System.out.println("Token Exception");
        }catch (ExpiredJwtException e){
            System.out.println("Expired Token Exception");
        }catch (UnsupportedJwtException e){
            System.out.println("Unsupported Exception");
        }
        return false;
    }

}
