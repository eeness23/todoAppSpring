package com.enes.fullstacktodoapp.full_stack_todo_app.Security;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static com.enes.fullstacktodoapp.full_stack_todo_app.Security.SecurityConstants.HEADER_STRING;
import static com.enes.fullstacktodoapp.full_stack_todo_app.Security.SecurityConstants.TOKEN_PREFIX;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtFunctions jwtFunctions;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwt = getTokenFromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt) && jwtFunctions.checkTokenValid(jwt)){
                Long userId = jwtFunctions.getIdFromToken(jwt);
                User user = (User) userDetailService.loadUserById(userId);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }catch (Exception e){
            System.out.println(e + " Filter Exception");
        }


        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(HEADER_STRING);

        if(StringUtils.hasText(header)&&header.startsWith(TOKEN_PREFIX)){
            return header.substring(7,header.length());
        }

        return null;
    }
}
