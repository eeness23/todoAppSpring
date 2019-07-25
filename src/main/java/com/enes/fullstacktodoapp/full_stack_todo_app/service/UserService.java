package com.enes.fullstacktodoapp.full_stack_todo_app.service;

import com.enes.fullstacktodoapp.full_stack_todo_app.exception.UsernameAlreadyExistsExeption;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import com.enes.fullstacktodoapp.full_stack_todo_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User register(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            return userRepository.save(user);

        } catch (Exception e) {
            throw new UsernameAlreadyExistsExeption("Username : " + user.getUsername() + " already exists");
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
