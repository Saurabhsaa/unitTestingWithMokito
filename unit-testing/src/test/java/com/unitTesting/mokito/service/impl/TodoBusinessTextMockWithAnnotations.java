package com.unitTesting.mokito.service.impl;

import com.unitTesting.mokito.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessTextMockWithAnnotations {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusiness;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void testRetriveToDoRelatedToSpringMock(){
        Mockito.when(todoServiceMock.retriveTodos("Test")).
                thenReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));
        List<String> res = todoBusiness.retriveToDoRelatedToSpring("Test");
        Assert.assertEquals(3,res.size());
    }

    @Test
    public void testRetriveToDoRelatedToSpringMockBDDStyle(){
        //given
        BDDMockito.given(todoServiceMock.retriveTodos("Test")).
                willReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));
        //when
        List<String> res = todoBusiness.retriveToDoRelatedToSpring("Test");

        //then
        Assert.assertThat(res.size(), Matchers.is(3));
    }

    @Test
    public void testDeleteToDoMethodCallBddStyle(){
        //given
        BDDMockito.given(todoServiceMock.retriveTodos("Test"))
                .willReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));

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
        BDDMockito.given(todoServiceMock.retriveTodos("Test"))
                .willReturn(Arrays.asList("Spring MVC","Spring Boot","Spring Oauth 2.0","Mokito","Java 15"));
        //when
        todoBusiness.deleteToDoRelatedNotToSpring("Test");

        //then
        BDDMockito.then(todoServiceMock).should(Mockito.atLeast(1)).deleteTodods(argumentCaptor.capture());
        Assert.assertThat(Arrays.asList("Mokito","Java 15"),Matchers.is(argumentCaptor.getAllValues()));
    }

}
