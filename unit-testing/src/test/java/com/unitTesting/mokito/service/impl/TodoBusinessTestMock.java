package com.unitTesting.mokito.service.impl;

import com.unitTesting.mokito.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class TodoBusinessTestMock {

    @Test
    public void testRetriveToDoRelatedToSpringMock(){
        TodoService todoServiceMock = Mockito.mock(TodoService.class);
        Mockito.when(todoServiceMock.retriveTodos("Test")).
                thenReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);
        List<String> res = todoBusiness.retriveToDoRelatedToSpring("Test");
        Assert.assertEquals(3,res.size());
    }

    @Test
    public void testRetriveToDoRelatedToSpringMockBDDStyle(){
        //given
        TodoService todoServiceMock = Mockito.mock(TodoService.class);
        BDDMockito.given(todoServiceMock.retriveTodos("Test")).
                willReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        //when
        List<String> res = todoBusiness.retriveToDoRelatedToSpring("Test");

        //then
        Assert.assertThat(res.size(), Matchers.is(3));
    }

    @Test
    public void testDeleteToDoMethodCallBddStyle(){
        //given
        TodoService todoServiceMock = Mockito.mock(TodoService.class);
        BDDMockito.given(todoServiceMock.retriveTodos("Test"))
                .willReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        //when
        todoBusiness.deleteToDoRelatedNotToSpring("Test");

        //then check whether delteTodomethod is get called or not
        Mockito.verify(todoServiceMock).deleteTodods("Mokito");
        Mockito.verify(todoServiceMock).deleteTodods("Java 15");
        Mockito.verify(todoServiceMock,Mockito.never()).deleteTodods("Spring MVC");
        BDDMockito.then(todoServiceMock).should(Mockito.never()).deleteTodods("Spring MVC");
        Mockito.verify(todoServiceMock,Mockito.times(1)).deleteTodods("Java 15");
    }

    @Test
    public void testArgunmentCapturingBddStyle(){
        //given
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        TodoService todoService = Mockito.mock(TodoService.class);
        BDDMockito.given(todoService.retriveTodos("Test"))
                .willReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        //when
        todoBusiness.deleteToDoRelatedNotToSpring("Test");

        //then
        BDDMockito.then(todoService).should(Mockito.atLeast(1)).deleteTodods(stringArgumentCaptor.capture());
        Assert.assertThat(Arrays.asList("Mokito","Java 15"),Matchers.is(stringArgumentCaptor.getAllValues()));
    }

}
