package com.unitTesting.mokito.service;

import java.util.List;

public interface TodoService {

    public List<String> retriveTodos(String user);

    public void deleteTodods(String user);

}
