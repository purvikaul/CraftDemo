package com.purvikaul.craftdemo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by purvi on 12/12/16.
 */
public class LoginResponse {
    private String accesstoken;
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(String accesstoken, String username) {
        this.accesstoken = accesstoken;
        this.username = username;
    }

    @JsonProperty
    public String getAccesstoken() {
        return accesstoken;
    }

    @JsonProperty
    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
    }
}
