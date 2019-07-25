package com.enes.fullstacktodoapp.full_stack_todo_app.service;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        if(!user.isPresent()){
            throw  new UsernameNotFoundException("Username not found");
        }
        return user.get();
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()){
            throw  new UsernameNotFoundException("Username not found");
        }
        return user.get();
    }

}
