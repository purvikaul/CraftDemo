package com.purvikaul.craftdemo.resources;

import com.purvikaul.craftdemo.CraftDemoApplication;
import com.purvikaul.craftdemo.CraftDemoConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientProperties;
import org.joda.time.Instant;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by purvi on 12/14/16.
 */
public class DonationResourceTest {
    @Rule
    public final DropwizardAppRule<CraftDemoConfiguration> RULE = new DropwizardAppRule<CraftDemoConfiguration>(CraftDemoApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

    static String  username = "auto"+ Instant.now().toString();

    public static final String queryAddDonation = "{\n" +
            "\"userid\": 1,\n"+
            "\"item\": \"cry2\",\n"+
            "\"value\": 1000,\n" +
            "\"deductible\": 300,\n" +
            "\"category\": \"child welfare3\",\n"+
            "\"timestamp\": 100002\n"+"}";





    /** Test Add Donation endpoint **/
    @Test
    public void checkAddDonationEndpoint() {
        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
        client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        client.property(ClientProperties.READ_TIMEOUT,    5000);
        Response response = client.target(
                String.format("http://localhost:%d/api/donations", RULE.getLocalPort()))
                .request()
                .post(Entity.entity(queryAddDonation, MediaType.APPLICATION_JSON));

//        assertThat(response.getStatus()).isEqualTo(200);
//
//        response = client.target(
//                String.format("http://localhost:%d/queryplan/execute", RULE.getLocalPort()))
//                .request()
//                .post(Entity.entity(queryFaulySignUp, MediaType.APPLICATION_JSON));
//
//        assertThat(response.getStatus()).isEqualTo(400);
    }

    /** Test Get Donations endpoint **/
    @Test
    public void checkGetDonationEndpoint() {
        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
        client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        client.property(ClientProperties.READ_TIMEOUT,    5000);
        Response response = client.target(
                String.format("http://localhost:%d/api/donations?userid=1", RULE.getLocalPort()))
                .request().get();

        assertThat(response.getStatus()).isEqualTo(200);

//        response = client.target(
//                String.format("http://localhost:%d/queryplan/execute", RULE.getLocalPort()))
//                .request()
//                .post(Entity.entity(queryFaultyLogin, MediaType.APPLICATION_JSON));
//
//        assertThat(response.getStatus()).isEqualTo(400);
    }


}
