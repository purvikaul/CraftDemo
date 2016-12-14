package com.purvikaul.craftdemo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by purvi on 12/12/16.
 */
public class LoginResponse {
    private String accesstoken;
    private Long userid;
    public LoginResponse() {
    }

    public LoginResponse(String accesstoken, Long userid) {
        this.accesstoken = accesstoken;
        this.userid = userid;
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
    public Long getUserid() {
        return userid;
    }

    @JsonProperty
    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
