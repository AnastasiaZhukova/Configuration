package com.github.anastasiazhukova.configuration.calculatorbackend.Calculator;

import com.github.anastasiazhukova.configuration.calculator.Result;

import static com.github.anastasiazhukova.configuration.constants.Constants.ERROR_MESSAGE;

public class Calculator implements ICalculator {

    private final String PLUS_SPLITTER = "\\+";

    @Override
    public Result calculateResult(final String pInput) {
        Result result;
        if (pInput.contains("+")) {
            result = countSum(pInput);
        } else {
            result = setErrorResult();
        }
        return result;
    }

    Result countSum(final String pInput) {
        Result result = new Result();
        try {
            double sum = 0;
            final String[] values = pInput.split(PLUS_SPLITTER);
            for (String value :
                    values) {
                sum += Double.valueOf(value);
            }
            result.setResult(String.valueOf(sum));
        } catch (final Exception pE) {
            result = setErrorResult();
        }
        return result;
    }

    private Result setErrorResult() {
        Result result = new Result();
        result.setError(ERROR_MESSAGE);
        return result;
    }
}
