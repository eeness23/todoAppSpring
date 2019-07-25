package com.enes.fullstacktodoapp.full_stack_todo_app.service;

import com.enes.fullstacktodoapp.full_stack_todo_app.exception.UsernameAlreadyExistsExeption;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import com.enes.fullstacktodoapp.full_stack_todo_app.repository.UserRepository;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User register(User user) {
        try{
            user.setPassword(encoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            return userRepository.save(user);

        }catch (Exception e){
            throw new UsernameAlreadyExistsExeption("Username : "+user.getUsername() + " already exists");
        }
    }

}
