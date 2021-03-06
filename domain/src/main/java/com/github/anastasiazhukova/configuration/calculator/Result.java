package com.github.anastasiazhukova.configuration.calculator;

import com.google.gson.annotations.SerializedName;

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
