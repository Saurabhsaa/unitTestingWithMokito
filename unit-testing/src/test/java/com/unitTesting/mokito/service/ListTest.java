package com.unitTesting.mokito.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

public class ListTest {

    @Test
    public void listTest(){
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.size()).thenReturn(2);
        Assert.assertEquals(2,listMock.size());
    }

    @Test
    public void listTestMultipleReturn(){
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.size()).thenReturn(2).thenReturn(3);
        Assert.assertEquals(2,listMock.size());
        Assert.assertEquals(3,listMock.size());
    }

    @Test
    public void listTestGet(){
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.get(0)).thenReturn("mockitoTest");
        Assert.assertEquals("mockitoTest",listMock.get(0));
        Assert.assertEquals(null,listMock.get(1));
    }

    //Argunment Macher
    @Test
    public void listTestGetAnyNumber(){
        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.get(Mockito.anyInt())).thenReturn("Mockito Testing");
        Assert.assertEquals("Mockito Testing",mockList.get(10));
        Assert.assertEquals("Mockito Testing",mockList.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void testListMockitoException(){
        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.get(1)).thenThrow(new RuntimeException("Testing excepton mocking"));
        mockList.get(1);
    }

    @Test
    public void testListMockitoBDDStyle(){
        //given
        List<String> mockList = Mockito.mock(List.class);
        BDDMockito.given(mockList.get(Mockito.anyInt())).willReturn("Mockito Test In BDDStyle");

        //when
        String res = mockList.get(7);

        //then
        Assert.assertThat(res, Matchers.is("Mockito Test In BDDStyle"));
    }
}
