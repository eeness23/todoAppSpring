package com.enes.fullstacktodoapp.full_stack_todo_app.service;

import com.enes.fullstacktodoapp.full_stack_todo_app.exception.DependenciesException;
import com.enes.fullstacktodoapp.full_stack_todo_app.exception.TaskExection;
import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import com.enes.fullstacktodoapp.full_stack_todo_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveOrUpdate(Task task) {
        if(task.getId()!=null){
            task.setUpdated_at(new Date());
        }
        if (task.isCompleted()) {
            checkDependencies(task);
        }
        try {
            // When we want to update task, we must check child task for different between before and after child
            if (checkDifferenceBetweenBandF(task)) {
                Task oldTask=findTaskById(task.getId()).get();
                List<Task> oldChildTasks = oldTask.getSubTasks();
                List<Task> newChildTasks = task.getSubTasks();
                for(Task oldChild : oldChildTasks ){
                    if(oldChildFind(oldChild,newChildTasks)){
                        newChildTasks.remove(oldChild);
                        oldChild.setParent(null);
                        taskRepository.save(oldChild);
                    }
                }
                for(Task newChild : newChildTasks){
                    newChild.setParent(task);
                    taskRepository.save(newChild);
                }
                task.setSubTasks(newChildTasks);
                return taskRepository.save(task);
            } else {
                if (task.getSubTasks().size() > 0) {
                    taskRepository.save(task);
                    for (Task subTask : task.getSubTasks()) {
                        subTask.setParent(task);
                        taskRepository.save(subTask);
                    }
                    return task;
                } else {
                    return taskRepository.save(task);
                }
            }
        } catch (Exception e) {
            throw new TaskExection("Task Id :" + task.getTaskIdentifier() + " already exists");
        }
    }

    private boolean checkDifferenceBetweenBandF(Task task) {
        if (task.getId() == null) {
            return false;
        }else{
            List<Long> oldChildIds=new ArrayList<>();
            List<Long> newChildIds=new ArrayList<>();
            Optional<Task> dbTask = findByTaskIdentifier(task.getTaskIdentifier());
            for(Task s : dbTask.get().getSubTasks()){
                oldChildIds.add(s.getId());
            }
            for(Task s : task.getSubTasks()){
                newChildIds.add(s.getId());
            }
            return !oldChildIds.equals(newChildIds);
        }
    }


    private boolean oldChildFind(Task task,List<Task> newTasks){
        for(Task s : newTasks){
            if(s.getId().equals(task.getId())){
                return false;
            }
        }
        return true;
    }

    private void checkDependencies(Task task) {
        if (task.getSubTasks().size() != 0) {
            List<String> notCompleted = new ArrayList<>();

            for (Task subTask : task.getSubTasks()) {
                if (!subTask.isCompleted()) {
                    notCompleted.add(subTask.getTaskIdentifier());
                }
            }
            if (!notCompleted.isEmpty()) {
                String names = String.join(" - ", notCompleted);
                throw new DependenciesException("You have to completed : " + names);
            }
        }
    }

    public Optional<Task> findByTaskIdentifier(String s) {

        Optional<Task> task = taskRepository.findByTaskIdentifier(s.toUpperCase());

        if (!task.isPresent()) {
            throw new TaskExection("Task Id : " + s + " not found");
        }

        return task;
    }


    public List<Task> findAll() {
        if (taskRepository.findAll() != null) {
            return taskRepository.findAll();
        }
        throw new TaskExection("There are no active tasks");
    }

    public void deleteByTaskIdentifier(String taskIdentifier) {
        Optional<Task> task = taskRepository.findByTaskIdentifier(taskIdentifier);

        if (!task.isPresent()) {
            throw new TaskExection("Task Id :" + taskIdentifier + " doesnt found");
        }
        if (task.get().getSubTasks().size() > 0) {
            for (Task subTask : task.get().getSubTasks()) {
                subTask.setParent(null);
            }
        }
        taskRepository.deleteByTaskIdentifier(taskIdentifier);

    }

    // will take all tasks if parent is null. For create new task
    public List<Task> getAllByParentIsNull() {
        return taskRepository.getAllByParentIsNull();
    }

    //will take all tasks if parent is null or parent is itself
    public List<Task> getTasksParentIsNullOrParentIsSelf(String taskIdentifier) {
        Optional<Task> task = findByTaskIdentifier(taskIdentifier);
        List<Task> tasks = taskRepository.findAll();
        List<Task> newTask = new ArrayList<>();

        for (Task tempTask : tasks) {
            if (!tempTask.equals(task.get()) && (tempTask.getParent() == null || tempTask.getParent().equals(task.get()))) {
                if (tempTask.getSubTasks().size() > 0 && !tempTask.getSubTasks().contains(task.get())) {
                    newTask.add(tempTask);
                } else if (tempTask.getSubTasks().size() == 0) {
                    newTask.add(tempTask);
                }
            }
        }

        return newTask;
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
