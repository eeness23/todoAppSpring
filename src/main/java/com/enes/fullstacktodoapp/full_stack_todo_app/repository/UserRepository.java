package com.enes.fullstacktodoapp.full_stack_todo_app.repository;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
