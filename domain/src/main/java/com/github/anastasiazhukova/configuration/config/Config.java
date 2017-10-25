package com.github.anastasiazhukova.configuration.config;

import com.google.gson.annotations.SerializedName;

public class Config {

    @SerializedName("version")
    int version;

    @SerializedName("name")
    String name;

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

}
