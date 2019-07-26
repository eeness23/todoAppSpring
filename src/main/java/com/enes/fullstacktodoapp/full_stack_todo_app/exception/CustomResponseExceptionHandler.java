package com.enes.fullstacktodoapp.full_stack_todo_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleTaskIdException(WebRequest request, TaskExection ex){
        TaskExceptionResponse taskExceptionResponse = new TaskExceptionResponse(ex.getMessage());
        return new ResponseEntity(taskExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameException(WebRequest request, UsernameAlreadyExistsExeption ex){
        UsernameAlreadyExistsResponse usernameAlreadyExistsResponse = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity(usernameAlreadyExistsResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDependenciesException(WebRequest request, DependenciesException ex){
        DependenciesExceptionResponse dependenciesExceptionResponse = new DependenciesExceptionResponse(ex.getMessage());
        return new ResponseEntity(dependenciesExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
