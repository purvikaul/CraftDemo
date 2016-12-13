package com.purvikaul.craftdemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

/**
 * Created by purvi on 12/12/16.
 */
@MongoCollection(name="Donations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Donation {

    private String id;
    private String username;
    private String item;
    private Double value;
    private String category;
    private Double deductible;
    private Long timestamp;

    public Donation() {
    }

    public Donation(String item, Double value, String category, Double deductible, Long timestamp) {
        this.item = item;
        this.value = value;
        this.category = category;
        this.deductible = deductible;
        this.timestamp = timestamp;
    }

    @ObjectId
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
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
