package com.github.anastasiazhukova.configuration.calculatorapi;

public interface ICalculatorApi {

    String calculateSum(final double pValue1, final double pValue2);

    String calculateProduct(final double pValue1, final double pValue2);

    String evaluate(final String pInput);

}
