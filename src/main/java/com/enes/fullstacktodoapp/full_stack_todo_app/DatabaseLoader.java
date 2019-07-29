package com.enes.fullstacktodoapp.full_stack_todo_app;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.TaskService;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        Task task1 = new Task("task 1","task1","description task 1");
        Task task2 = new Task("task 2","task2","description task 2");
        Task task3 = new Task("abc","abc","description task abc");
        Task task4 = new Task("abc2","abc2","description task abc2");
        Task task5 = new Task("test","test","description task test");
        Task task6 = new Task("test","task3","description task 3");
        Task task7 = new Task("task 7","task7","description task 7");
        Task task8 = new Task("test task 8","task8","description task 8");
        Task task9 = new Task("test task 9","task9","description task 9");

        task2.setParent(task1);
        task3.setParent(task1);
        taskService.saveOrUpdate(task1);
        taskService.saveOrUpdate(task2);
        taskService.saveOrUpdate(task3);
        taskService.saveOrUpdate(task4);
        taskService.saveOrUpdate(task5);
        task6.setParent(task5);
        taskService.saveOrUpdate(task6);
        taskService.saveOrUpdate(task7);
        taskService.saveOrUpdate(task8);
        taskService.saveOrUpdate(task9);

        User user = new User();
        user.setPassword("test123");
        user.setUsername("test@gmail.com");
        user.setFullName("enes demirbaş");
        userService.register(user);


    }
}
