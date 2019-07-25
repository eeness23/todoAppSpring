package com.enes.fullstacktodoapp.full_stack_todo_app.repository;

import com.enes.fullstacktodoapp.full_stack_todo_app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
