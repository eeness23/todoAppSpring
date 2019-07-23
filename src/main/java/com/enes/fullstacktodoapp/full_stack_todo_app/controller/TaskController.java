package com.enes.fullstacktodoapp.full_stack_todo_app.controller;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.MapValidationService;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private MapValidationService mapValidationService;


    @PostMapping("")
    public ResponseEntity<?> createTase(@Valid @RequestBody Task task, BindingResult bindingResult){

         ResponseEntity<?> errors = mapValidationService.mapValidationService(bindingResult);
         if(errors!=null){
             return errors;
         }

         Task tempTask= taskService.saveOrUpdate(task);

        return new ResponseEntity<>(tempTask, HttpStatus.CREATED);
    }
}
