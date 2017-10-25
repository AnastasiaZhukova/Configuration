package com.github.anastasiazhukova.configuration.calculator;

import com.google.gson.annotations.SerializedName;

import static com.github.anastasiazhukova.configuration.constants.Constants.ERROR_MESSAGE;

public class Result {

    @SerializedName("result")
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(final String pResult) {
        result = pResult;
    }

    public void setError(final String pError) {
        result = pError;
    }

}
