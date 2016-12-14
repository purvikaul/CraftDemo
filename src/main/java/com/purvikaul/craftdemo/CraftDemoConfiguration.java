package com.purvikaul.craftdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by purvi on 12/11/16.
 */
public class CraftDemoConfiguration extends Configuration {
    /**
     * A factory used to connect to a relational database management system.
     * Factories are used by Dropwizard to group together related configuration
     * parameters such as database connection driver, URI, password etc.
     */
    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();


    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    /**
     * A getter for the database factory.
     *
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */


    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

//    @JsonProperty
//    public String getMongohost() {
//        return mongohost;
//    }
//
//    @JsonProperty
//    public void setMongohost(String mongohost) {
//        this.mongohost = mongohost;
//    }
//
//    @JsonProperty
//    public int getMongoport() {
//        return mongoport;
//    }
//
//    @JsonProperty
//    public void setMongoport(int mongoport) {
//        this.mongoport = mongoport;
//    }
//
//    @JsonProperty
//    public String getDb() {
//        return db;
//    }
//
//    @JsonProperty
//    public void setDb(String db) {
//        this.db = db;
//    }
}
