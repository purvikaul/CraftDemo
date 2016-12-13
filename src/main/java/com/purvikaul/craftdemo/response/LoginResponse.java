package com.purvikaul.craftdemo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by purvi on 12/12/16.
 */
public class LoginResponse {
    private String accesscode;
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(String accesscode, String username) {
        this.accesscode = accesscode;
        this.username = username;
    }

    @JsonProperty
    public String getAccesscode() {
        return accesscode;
    }

    @JsonProperty
    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
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
