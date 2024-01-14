package com.unitTesting.mokito.service.impl;

import com.unitTesting.mokito.service.TodoService;
import com.unitTesting.mokito.service.TodoServiceStub;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TodoBusinessTest {

    @Test
    public void testRetriveToDoRelatedToSpring(){
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);
        List<String> res = todoBusiness.retriveToDoRelatedToSpring("test");
        Assert.assertEquals(3,res.size());
    }


}
