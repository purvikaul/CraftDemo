package com.purvikaul.craftdemo.healthcheck;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by purvi on 12/12/16.
 */
public class MongoHealthCheck extends HealthCheck{
    public MongoHealthCheck() {
    }
    protected Result check() throws Exception {
//        mongo.getUsedDatabases();
        return Result.healthy();
    }
}
