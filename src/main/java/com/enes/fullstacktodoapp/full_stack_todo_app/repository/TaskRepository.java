package com.enes.fullstacktodoapp.full_stack_todo_app.repository;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task,Long> {
    Optional<Task> findByTaskIdentifier(String s);
    void deleteByTaskIdentifier(String s);
    List<Task> getAllByParentIsNull();
}
