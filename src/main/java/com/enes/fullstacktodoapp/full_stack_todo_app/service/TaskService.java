package com.enes.fullstacktodoapp.full_stack_todo_app.service;

import com.enes.fullstacktodoapp.full_stack_todo_app.exception.TaskExection;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import com.enes.fullstacktodoapp.full_stack_todo_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveOrUpdate(Task task){
        try {
            return taskRepository.save(task);
        }catch (Exception e){
            throw new TaskExection("Task Id :"+task.getProjectIdentifier()+" already exists");
        }

    }
}
