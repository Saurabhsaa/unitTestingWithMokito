package com.unitTesting.mokito.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersTest {

    @Test
    public void testHamcreatMatcherMethods(){
        List<Integer> res = Arrays.asList(22,12,32,25,43,52,01,55);
        Assert.assertThat(res, Matchers.hasSize(8));
        Assert.assertThat(res,Matchers.hasItems(22,12));
        Assert.assertThat(res,Matchers.everyItem(Matchers.greaterThan(00)));
        Assert.assertThat(res,Matchers.everyItem(Matchers.lessThanOrEqualTo(55)));

        //Strings
        Assert.assertThat("",Matchers.isEmptyString());
        Assert.assertThat(null,Matchers.isEmptyOrNullString());

        //Arrays
        Integer[] intArray = {1,2,4,5};
        Assert.assertThat(intArray,Matchers.arrayWithSize(4));
        Assert.assertThat(intArray,Matchers.arrayContainingInAnyOrder(2,1,5,4));
        Assert.assertThat(intArray,Matchers.arrayContaining(1,2,4,5));

    }

}
