package com.enes.fullstacktodoapp.full_stack_todo_app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskExection extends RuntimeException {

    public TaskExection(String s) {
        super(s);
    }
}
