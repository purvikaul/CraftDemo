package com.purvikaul.craftdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by purvi on 12/11/16.
 */
public class CraftDemoConfiguration extends Configuration {
    @JsonProperty
    @NotEmpty
    public String mongohost ;

    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport;
    @JsonProperty
    @NotEmpty
    public String db;

    @JsonProperty
    public String getMongohost() {
        return mongohost;
    }

    @JsonProperty
    public void setMongohost(String mongohost) {
        this.mongohost = mongohost;
    }

    @JsonProperty
    public int getMongoport() {
        return mongoport;
    }

    @JsonProperty
    public void setMongoport(int mongoport) {
        this.mongoport = mongoport;
    }

    @JsonProperty
    public String getDb() {
        return db;
    }

    @JsonProperty
    public void setDb(String db) {
        this.db = db;
    }
}
