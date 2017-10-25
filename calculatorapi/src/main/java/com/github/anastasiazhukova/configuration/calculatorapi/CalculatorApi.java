package com.github.anastasiazhukova.configuration.calculatorapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CalculatorApi implements ICalculatorApi {

    private String mCalculatorURL;

    public CalculatorApi(final String pCalculatorURL) {
        if (pCalculatorURL.endsWith("/")) {
            mCalculatorURL = pCalculatorURL;
        } else {
            mCalculatorURL = pCalculatorURL + "/";
        }
    }

    @Override
    public String calculateSum(final double pValue1, final double pValue2) {
        return evaluate(pValue1 + "+" + pValue2);
    }

    @Override
    public String evaluate(final String pInput) {
        try {
            return mCalculatorURL + CalculatorApiConstants.CALCULATE + URLEncoder.encode(pInput, "UTF-8");
        } catch (final UnsupportedEncodingException pE) {
            throw new IllegalStateException(pE);
        }
    }
}
