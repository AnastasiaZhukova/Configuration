package com.github.anastasiazhukova.configuration.calculator;

import com.github.anastasiazhukova.configuration.calculatorapi.CalculatorApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CalculatorClientTest {

    @Mock
    private CalculatorApi mCalculatorApi;
    @Mock
    private CalculatorClient mCalculatorClient;
    @Captor
    private ArgumentCaptor<CalculatorClient.CalculatorResponseListener> mCaptor;

    @Before
    public void setUp() {
        mCalculatorApi = mock(CalculatorApi.class);
        mCalculatorClient = spy(CalculatorClient.class);
        mCaptor = ArgumentCaptor.forClass(CalculatorClient.CalculatorResponseListener.class);
    }

    private final String rightURL = "https://add-calculator.appspot.com/calculate?input=1%2B2";

    @Test
    public void makeRequest() {

        //actually, this doReturn example does not have any practical use
        //but I understand that doReturn will return us value without calling a method
        doReturn(rightURL).when(mCalculatorApi).evaluate(anyString());
        verify(mCalculatorApi, never()).evaluate(anyString());
        final String url = mCalculatorApi.evaluate("any string");

        final CalculatorClient.CalculatorResponseListener listener = new CalculatorClient.CalculatorResponseListener();
        mCalculatorClient.makeRequest(url, listener);
        verify(mCalculatorClient).makeRequest(eq(rightURL), mCaptor.capture());
        assertEquals(listener.getResult().getResult(), mCaptor.getValue().getResult().getResult());

    }

}