package com.github.anastasiazhukova.configuration.configuration;

import com.github.anastasiazhukova.configuration.config.Config;
import com.github.anastasiazhukova.configuration.httpclient.HttpClient;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigurationClient {

    void makeRequest(final String url, ConfigurationClient.ConfigurationResponseListener pMyResponseListener) {
        new HttpClient().request(url, pMyResponseListener);
        if (pMyResponseListener.getThrowable() != null) {
            throw new UnsupportedOperationException(pMyResponseListener.getThrowable());
        }
    }

    static class ConfigurationResponseListener implements HttpClient.ResponseListener {

        private Config mConfig;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                mConfig = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, Config.class);
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

        public Config getConfig() {
            return mConfig;
        }
    }

}
