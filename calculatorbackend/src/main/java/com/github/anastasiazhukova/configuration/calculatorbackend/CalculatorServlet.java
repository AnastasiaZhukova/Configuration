/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.anastasiazhukova.configuration.calculatorbackend;

import com.github.anastasiazhukova.configuration.calculator.Result;
import com.github.anastasiazhukova.configuration.calculatorbackend.Calculator.Calculator;
import com.github.anastasiazhukova.configuration.calculatorbackend.Calculator.ICalculator;
import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.github.anastasiazhukova.configuration.constants.Constants.ERROR_MESSAGE;

public class CalculatorServlet extends HttpServlet {

    @Override
    public void doGet(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws IOException {

        final String PARAMETER_INPUT = "input";
        pResponse.setContentType("application/json");
        Result result;
        final ICalculator calculator;
        try {
            String input = pRequest.getParameter(PARAMETER_INPUT);
            calculator = new Calculator();
            result = calculator.calculateResult(input);
        } catch (final Exception pE) {
            result = new Result();
            result.setError(ERROR_MESSAGE);
        }

        new Gson().toJson(result, pResponse.getWriter());
    }

    @Override
    public void doPost(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws IOException {
        pResponse.setContentType("text/plain");
        pResponse.getWriter().println("Hello");
    }

}
