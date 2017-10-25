package com.github.anastasiazhukova.configuration.calculator;

public interface ICalculator {

    String add(double pValue1, double pValue2) throws Exception;

    String evaluate(String pInput) throws Exception;
}
