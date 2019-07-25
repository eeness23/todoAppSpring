package com.enes.fullstacktodoapp.full_stack_todo_app.controller;

import com.enes.fullstacktodoapp.full_stack_todo_app.Security.JwtFunctions;
import com.enes.fullstacktodoapp.full_stack_todo_app.Security.SecurityConstants;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import com.enes.fullstacktodoapp.full_stack_todo_app.payload.JwtTokenResponse;
import com.enes.fullstacktodoapp.full_stack_todo_app.payload.LoginRequest;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.MapValidationService;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.UserService;
import com.enes.fullstacktodoapp.full_stack_todo_app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.enes.fullstacktodoapp.full_stack_todo_app.Security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MapValidationService mapValidationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtFunctions jwtFunctions;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        ResponseEntity<?> errorMap = mapValidationService.mapValidationService(bindingResult);
        if(errorMap !=null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = TOKEN_PREFIX +  jwtFunctions.generateToken(authentication);

        return ResponseEntity.ok(new JwtTokenResponse(true,token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult){

        if(user.getPassword()!=null){
            userValidator.validate(user,bindingResult);
        }

        ResponseEntity<?> errorMap = mapValidationService.mapValidationService(bindingResult);
        if(errorMap!=null) return errorMap;

        User newUser = userService.register(user);
        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}
