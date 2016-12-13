package com.purvikaul.craftdemo;


import com.mongodb.MongoClient;
import com.purvikaul.craftdemo.healthcheck.MongoHealthCheck;
import com.purvikaul.craftdemo.resources.DonationResource;
import com.purvikaul.craftdemo.resources.LoginResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by purvi on 12/12/16.
 */
public class CraftDemoApplication extends Application<CraftDemoConfiguration> {
    public static void main(String[] args) throws Exception {
        new CraftDemoApplication().run(args);
    }
    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<CraftDemoConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets","/","index.html"));
    }

    public void run(CraftDemoConfiguration craftDemoConfiguration, Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/api/*");
        MongoClient mongo = new MongoClient(craftDemoConfiguration.mongohost,craftDemoConfiguration.mongoport);
        final LoginResource loginResource = new LoginResource(mongo,craftDemoConfiguration);
        final DonationResource donationResource = new DonationResource(mongo,craftDemoConfiguration);
        environment.jersey().register(loginResource);
        environment.jersey().register(donationResource);
        environment.healthChecks().register("Mongo", new MongoHealthCheck(mongo));

    }
}
