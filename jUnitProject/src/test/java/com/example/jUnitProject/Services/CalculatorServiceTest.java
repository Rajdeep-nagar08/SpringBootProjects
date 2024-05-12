package com.example.jUnitProject.Services;

import org.junit.*;

import java.util.Date;

public class CalculatorServiceTest {

    // JUnit 4

    // @BeforeClass (execute before first test),

    // @Befor (execute before every test)

    // @AfterClass (exceute after last test),

    // @After (exceute after the end of each test, so no. of times it execute = no of test methods in the class)

    // @Test(timeout=2000), testcase will fail if time to execute it > 2000ms


    @BeforeClass
    public static void init(){

        System.out.println("Before all test cases !!!");

        System.out.println("Started test: "+new Date());

        // connection open or file open
    }

    @Test
    public void addTwoNumberTest(){

        System.out.println("Test for addTwoNumberTest()");

        int result=CalculatorService.addTwoNumber(12,45);

        int expected=57;

        Assert.assertEquals(expected,result);

    }


    @Test
    public void sumAnyNumberTest(){

        System.out.println("Test for sumAnyNumberTest()");

        int result = CalculatorService.sumAnyNumber(2,6,7,9);

        int expectedResult=24;

        Assert.assertEquals(expectedResult,result);
    }


    @AfterClass
    public static void cleanUp(){
        System.out.println("After all test cases !!!");
        System.out.println("End test cases : "+new Date());
    }


    // JUnit 5

    /*

    JUnit 5 is composed of different modules,



     */

}
