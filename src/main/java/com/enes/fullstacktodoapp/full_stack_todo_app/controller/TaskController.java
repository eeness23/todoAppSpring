package com.enes.fullstacktodoapp.full_stack_todo_app.controller;

import com.enes.fullstacktodoapp.full_stack_todo_app.exception.TaskExection;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.MapValidationService;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{taskIdentifier}")
    public ResponseEntity<?> getTask(@PathVariable String taskIdentifier){
        Optional<Task> task = taskService.findByProjectIdentifier(taskIdentifier);
        return new ResponseEntity<>(task.get(),HttpStatus.ACCEPTED);
    }

    @GetMapping("")
    public List<Task> getTasks(){
       return taskService.findAll();
    }

    @DeleteMapping("/{taskIdentifier}")
    public ResponseEntity<String> deleteTask(@PathVariable String taskIdentifier){
        taskService.deleteByProjectIdentifier(taskIdentifier);
        return new ResponseEntity<String>("Task Id : "+taskIdentifier+" not found",HttpStatus.OK);
    }
}
