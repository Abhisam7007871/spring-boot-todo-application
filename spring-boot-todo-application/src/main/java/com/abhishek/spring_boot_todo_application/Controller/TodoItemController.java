package com.abhishek.spring_boot_todo_application.Controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class TodoItemController {
    private final Loggerlogger = LoggerFactory.getLogger(todoItemController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/")
    public ModelAndView index(){
        logger.info("request toGET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems",todoItemRepository.findAll());
        modelAndView.addObject("today",Instant.now().atZone(ZoneId.system.Default()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-todo-item";
        }

        todoItem.setCreateDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";


    }


    @PoostMapping("/todo/{id}")
    public String updateTodoIItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model){
        if(result.hasErrors()){
            todoItem.setId(id);
            return "Update-todo-item";
        }

        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";

    }




}
