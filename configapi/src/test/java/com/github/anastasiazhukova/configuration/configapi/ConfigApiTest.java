package com.github.anastasiazhukova.configuration.configapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigApiTest {

    private static final String mConfigURL = "something.com";
    private ConfigApi mConfigApi;
    private String mBaseForResponse = mConfigURL + "/getconfig?";

    @Before
    public void setUp() throws Exception {
        mConfigApi = new ConfigApi(mConfigURL);

    }

    @Test
    public void getConfig() throws Exception {
        assertEquals(mBaseForResponse, mConfigApi.getConfig());
    }

}