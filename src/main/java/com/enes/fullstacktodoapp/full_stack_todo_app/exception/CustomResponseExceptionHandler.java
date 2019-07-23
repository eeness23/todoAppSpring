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
    public final ResponseEntity<Object> handleTaskIdException(WebRequest request, TaskExection taskExection){
        TaskExceptionResponse taskExceptionResponse = new TaskExceptionResponse(taskExection.getMessage());
        return new ResponseEntity(taskExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
