package com.enes.fullstacktodoapp.full_stack_todo_app;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import com.enes.fullstacktodoapp.full_stack_todo_app.repository.TaskRepository;
import com.enes.fullstacktodoapp.full_stack_todo_app.service.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void getAllTask(){
        Mockito.when(taskRepository.findAll()).thenReturn(Stream.of(new Task("TEST_NAME_1","TEST_ID_1","TEST_DESC_1"),new Task("TEST_NAME_2","TEST_ID_2","TEST_DESC_2")).collect(Collectors.toList()));
        Assert.assertEquals(2,taskService.findAll().size());
   }

   @Test
   public void checkSave(){
       Mockito.when(taskRepository.save(Mockito.any())).thenReturn(new Task("TEST_NAME_1","TEST_ID_1","TEST_DESC_1"));
       Assert.assertEquals("TEST_ID_1",taskService.saveOrUpdate(new Task()).getTaskIdentifier());
   }

   @Test
    public void findOne(){
       Mockito.when(taskRepository.findByTaskIdentifier(Mockito.any())).thenReturn(Optional.of(new Task("TEST_NAME_1", "TEST_ID_1", "TEST_DESC_1")));
       Assert.assertEquals("TEST_ID_1",taskService.findByTaskIdentifier("TEST_ID_1").get().getTaskIdentifier());
   }



}
