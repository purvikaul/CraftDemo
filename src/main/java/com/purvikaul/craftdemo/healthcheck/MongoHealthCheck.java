package com.purvikaul.craftdemo.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;

/**
 * Created by purvi on 12/12/16.
 */
public class MongoHealthCheck extends HealthCheck{
    private Mongo mongo;

    public MongoHealthCheck(Mongo mongo) {
        this.mongo = mongo;
    }
    protected Result check() throws Exception {
        mongo.getUsedDatabases();
        return Result.healthy();
    }
}
