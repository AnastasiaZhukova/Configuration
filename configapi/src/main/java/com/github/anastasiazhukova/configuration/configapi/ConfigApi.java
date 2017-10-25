package com.github.anastasiazhukova.configuration.configapi;

public class ConfigApi implements IConfigApi {

    private String mConfigURL;

    public ConfigApi(String pConfigURL) {
        if (pConfigURL.endsWith("/")) {
            mConfigURL = pConfigURL;
        } else {
            mConfigURL = pConfigURL + "/";
        }
    }

    @Override
    public String getConfig() {
        return mConfigURL + ConfigApiConstants.GETCONFIG;
    }

}
