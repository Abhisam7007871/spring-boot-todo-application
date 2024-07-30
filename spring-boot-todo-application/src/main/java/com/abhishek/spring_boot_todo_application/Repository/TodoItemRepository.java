package com.abhishek.spring_boot_todo_application.Repository;

import com.abhishek.spring_boot_todo_application.Model.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
}