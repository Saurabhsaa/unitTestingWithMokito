package com.unitTesting.mokito.service.impl;

import com.unitTesting.mokito.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

public class TodoBusinessImpl {

    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService){
        this.todoService = todoService;
    }

    public List<String> retriveToDoRelatedToSpring(String user){
        List<String> todos = todoService.retriveTodos(user);
        return todos.stream().filter(todo -> todo.contains("Spring")).collect(Collectors.toList());
    }

    public void deleteToDoRelatedNotToSpring(String user){
        List<String> todos = todoService.retriveTodos(user);
        for(String todo : todos){
            if(!todo.contains("Spring"))
                todoService.deleteTodods(todo);
        }
    }

}
