package com.unitTesting.mokito.service;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService{
    @Override
    public List<String> retriveTodos(String user) {
        return Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15");
    }

    @Override
    public void deleteTodods(String user) {

    }
}
