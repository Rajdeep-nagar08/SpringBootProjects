package com.example.jUnitProject.Services;

public class CalculatorService {


    public static int addTwoNumber(int a, int b){


        return a+b;

    }

    public static int productTwoNumber(int a, int b){


        return a+b;

    }

    public static double divideTwoNumber(int a, int b){


        return a/b;

    }

    public static int sumAnyNumber(int ...numbers){

        int s=0;

        for(int n:numbers){
            s+=n;
        }

        return s;
    }
}
