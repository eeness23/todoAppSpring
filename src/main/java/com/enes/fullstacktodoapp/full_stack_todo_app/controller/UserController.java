package com.enes.fullstacktodoapp.full_stack_todo_app.controller;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.MapValidationService;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private MapValidationService mapValidationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult){
        ResponseEntity<?> errorMap = mapValidationService.mapValidationService(bindingResult);
        if(errorMap!=null) return errorMap;

        User newUser = userService.register(user);
        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}
