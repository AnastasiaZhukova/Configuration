package com.github.anastasiazhukova.configuration.configuration;

import com.github.anastasiazhukova.configuration.Constants;
import com.github.anastasiazhukova.configuration.config.Config;
import com.github.anastasiazhukova.configuration.configapi.ConfigApi;

public class Configuration {

    private static int version;

    public static int getVersion() throws Exception {
        ConfigurationClient configurationClient = new ConfigurationClient();
        final String url = new ConfigApi(Constants.CONFIG_URL).getConfig();
        final ConfigurationClient.ConfigurationResponseListener listener = new ConfigurationClient.ConfigurationResponseListener();
        configurationClient.makeRequest(url, listener);
        Config config = listener.getConfig();
        if (config != null) {
            return config.getVersion();
        } else {
            throw new IllegalStateException("Error");
        }
    }

}
