package com.purvikaul.craftdemo;


import com.purvikaul.craftdemo.model.Donation;
import com.purvikaul.craftdemo.model.DonationDAO;
import com.purvikaul.craftdemo.model.User;
import com.purvikaul.craftdemo.model.UserDAO;
import com.purvikaul.craftdemo.resources.DonationResource;
import com.purvikaul.craftdemo.resources.LoginResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by purvi on 12/12/16.
 */
public class CraftDemoApplication extends Application<CraftDemoConfiguration> {
    private final HibernateBundle<CraftDemoConfiguration> hibernateBundle = new HibernateBundle<CraftDemoConfiguration>(User.class, Donation.class) {
        public DataSourceFactory getDataSourceFactory(CraftDemoConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    public static void main(String[] args) throws Exception {
        new CraftDemoApplication().run(args);
    }
    @Override
    public String getName() {
        return "Craft Demo";
    }

    @Override
    public void initialize(final Bootstrap<CraftDemoConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new AssetsBundle("/assets","/","index.html"));
    }

    public void run(CraftDemoConfiguration craftDemoConfiguration, Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/api/*");
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        final DonationDAO donationDAO = new DonationDAO(hibernateBundle.getSessionFactory());
        final LoginResource loginResource = new LoginResource(userDAO);
        final DonationResource donationResource = new DonationResource(donationDAO,userDAO);
        environment.jersey().register(loginResource);
        environment.jersey().register(donationResource);

    }
}
