package com.github.anastasiazhukova.configuration.httpclient;

public interface IHttpClient {

    void request(String url, HttpClient.ResponseListener listener);
}
