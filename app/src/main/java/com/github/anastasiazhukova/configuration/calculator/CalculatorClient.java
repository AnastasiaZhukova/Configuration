package com.github.anastasiazhukova.configuration.calculator;

import com.github.anastasiazhukova.configuration.httpclient.HttpClient;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class CalculatorClient {

    void makeRequest(final String url, CalculatorResponseListener pMyResponseListener) {
        new HttpClient().request(url, pMyResponseListener);
        if (pMyResponseListener.getThrowable() != null) {
            throw new UnsupportedOperationException(pMyResponseListener.getThrowable());
        }
    }

    static class CalculatorResponseListener implements HttpClient.ResponseListener {

        private Result result;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                result = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, Result.class);
            } finally {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception ignored) {
                    }
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        @Override
        public void onError(final Throwable pThrowable) {
            mThrowable = pThrowable;
        }

        public Result getResult() {
            return result;
        }
    }

}
