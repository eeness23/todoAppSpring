package com.enes.fullstacktodoapp.full_stack_todo_app.repository;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task,Long> {
}
