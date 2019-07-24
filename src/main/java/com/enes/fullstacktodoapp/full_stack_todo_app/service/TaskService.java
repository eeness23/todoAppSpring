package com.enes.fullstacktodoapp.full_stack_todo_app.service;

import com.enes.fullstacktodoapp.full_stack_todo_app.exception.TaskExection;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import com.enes.fullstacktodoapp.full_stack_todo_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveOrUpdate(Task task){
        try {
            return taskRepository.save(task);
        }catch (Exception e){
            throw new TaskExection("Task Id :"+task.getTaskIdentifier()+" already exists");
        }
    }

    public Optional<Task> findByTaskIdentifier(String s){

        Optional<Task> task=taskRepository.findByTaskIdentifier(s.toUpperCase());

        if(!task.isPresent()) {
            throw new TaskExection("Task Id : "+s+" not found");
        }

        return task;
    }


    public List<Task> findAll() {
        if(taskRepository.findAll()!=null){
            return taskRepository.findAll();
        }
        throw new TaskExection("There are no active tasks");
    }

    public void deleteByTaskIdentifier(String taskIdentifier) {
        Optional<Task> task = taskRepository.findByTaskIdentifier(taskIdentifier);

        if(!task.isPresent()){
            throw new TaskExection("Task Id :"+taskIdentifier+" doesnt found");
        }
        taskRepository.deleteByTaskIdentifier(taskIdentifier);

    }
}
