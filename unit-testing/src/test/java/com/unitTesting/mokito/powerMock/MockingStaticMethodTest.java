package com.unitTesting.mokito.powerMock;

import org.assertj.core.api.AssertionInfo;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class MockingStaticMethodTest {

//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void testSystemUnderTestWithPoerMock_methodCallingAStaticMethod(){
        //given
        BDDMockito.given(dependency.retrieveAllStats()).willReturn(Arrays.asList(1,2,3,4));
        PowerMockito.mockStatic(UtilityClass.class);
        BDDMockito.given(UtilityClass.staticMethod(10)).willReturn(150);

        //when
        int res = systemUnderTest.methodCallingAStaticMethod();

        //then
        Assert.assertThat(res, Matchers.is(150));

    }

}
