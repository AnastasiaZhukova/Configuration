package com.github.anastasiazhukova.configuration.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private ICalculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    private final String expectedResult = "20.0";

    @Test
    public void add() throws Exception {
        assertEquals(expectedResult, mCalculator.add(10, 10));
    }

    @Test
    public void evaluate() throws Exception {
        assertEquals(expectedResult, mCalculator.evaluate("10+10"));
    }

}