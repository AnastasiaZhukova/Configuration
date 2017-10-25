package com.github.anastasiazhukova.configuration.calculator;

import com.github.anastasiazhukova.configuration.Constants;
import com.github.anastasiazhukova.configuration.calculatorapi.CalculatorApi;

public class Calculator implements ICalculator {

    @Override
    public String add(final double pValue1, final double pValue2) throws Exception {
        return evaluate(pValue1 + "+" + pValue2);

    }

    @Override
    public String evaluate(final String pInput) throws Exception {
        final CalculatorClient calculatorClient = new CalculatorClient();
        final String url = new CalculatorApi(Constants.CALCULATOR_URL).evaluate(pInput);
        final CalculatorClient.CalculatorResponseListener listener = new CalculatorClient.CalculatorResponseListener();
        calculatorClient.makeRequest(url, listener);
        final Result result = listener.getResult();
        if (result != null) {
            return String.valueOf(listener.getResult().getResult());
        } else {
            throw new IllegalStateException("Error");
        }
    }
}
