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

        Task task1 = new Task("deneme task 1","task1","description task 1");
        Task task2 = new Task("deneme task 2","task2","description task 2");
        Task task3 = new Task("deneme task 3","abc","description task 3");
        Task task4 = new Task("deneme task 4","task4","description task 4");
        Task task5 = new Task("a1deneme task 5","task5","description task4");
        Task task6 = new Task("deneme task 6","task6","description task2");
        Task task7 = new Task("deneme task 7","bac","description tas 3");
        Task task8 = new Task("zdeneme task 8","zac","description tak 4");
        Task task9 = new Task("deneme task 9","task9","description tsk 1");

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
        user.setPassword("enes23");
        user.setUsername("enes@gmail.com");
        user.setFullName("enes demirbaş");
        userService.register(user);


    }
}
