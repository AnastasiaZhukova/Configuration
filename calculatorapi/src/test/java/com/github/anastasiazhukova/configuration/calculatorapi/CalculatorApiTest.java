package com.github.anastasiazhukova.configuration.calculatorapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorApiTest {

    private static final String mCalculatorURL = "something.com";
    private static final String mCalculatorURLWithSlash = "something.com/";
    private CalculatorApi mCalculatorApi;
    private CalculatorApi mCalculatorApiWithSlash;
    private final String mBaseForResponse = mCalculatorURL + "/calculate?input=";

    @Before
    public void setUp() throws Exception {
        mCalculatorApi = new CalculatorApi(mCalculatorURL);
        mCalculatorApiWithSlash = new CalculatorApi(mCalculatorURLWithSlash);
    }

    @Test
    public void calculateSum() throws Exception {
        final String input = "10.0%2B10.0";
        assertEquals(mBaseForResponse + input, mCalculatorApi.calculateSum(10, 10));
        assertEquals(mBaseForResponse + input, mCalculatorApiWithSlash.calculateSum(10, 10));

    }

    @Test
    public void calculateProduct() throws Exception {
        final String input = "10.0*10.0";
        assertEquals(mBaseForResponse + input, mCalculatorApi.calculateProduct(10, 10));
        assertEquals(mBaseForResponse + input, mCalculatorApiWithSlash.calculateProduct(10, 10));

    }

    @Test
    public void evaluate() throws Exception {
        final String input = "10%2B10";
        assertEquals(mBaseForResponse + input, mCalculatorApi.evaluate("10+10"));
        assertEquals(mBaseForResponse + input, mCalculatorApiWithSlash.evaluate("10+10"));
    }

}