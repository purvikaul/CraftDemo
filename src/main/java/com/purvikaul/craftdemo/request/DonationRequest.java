package com.purvikaul.craftdemo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by purvi on 12/12/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationRequest {



    private String item;
    private Double value;
    private String category;
    private Double deductible;
    private Long timestamp;
    private long userid;

    public DonationRequest() {
    }

    public DonationRequest(String item, Double value, String category, Double deductible, Long timestamp, long userid) {
        this.item = item;
        this.value = value;
        this.category = category;
        this.deductible = deductible;
        this.timestamp = timestamp;
        this.userid = userid;
    }

    @JsonProperty
    public long getUserid() {
        return userid;
    }

    @JsonProperty
    public void setUserid(long userid) {
        this.userid = userid;
    }

    @JsonProperty
    public String getItem() {
        return item;
    }

    @JsonProperty
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty
    public Double getValue() {
        return value;
    }

    @JsonProperty
    public void setValue(Double value) {
        this.value = value;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @JsonProperty
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty
    public Double getDeductible() {
        return deductible;
    }

    @JsonProperty
    public void setDeductible(Double deductible) {
        this.deductible = deductible;
    }

    @JsonProperty
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
