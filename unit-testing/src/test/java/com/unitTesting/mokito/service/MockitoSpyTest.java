package com.unitTesting.mokito.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

public class MockitoSpyTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ArrayList arrayList;

    @Test
    public void test(){
        BDDMockito.given(arrayList.size()).willReturn(5);
        arrayList.add("It");
        Assert.assertThat(5, Matchers.is(arrayList.size()));
    }

    @Test
    public void testSpy(){
        ArrayList arrList = Mockito.spy(ArrayList.class);
        Assert.assertThat(0, Matchers.is(arrayList.size()));
        BDDMockito.given(arrayList.size()).willReturn(5);
        arrList.add("It");
        Assert.assertThat(5, Matchers.is(arrayList.size()));
        Mockito.verify(arrList).add("It");
        Mockito.verify(arrList,Mockito.never()).clear();
    }

}
