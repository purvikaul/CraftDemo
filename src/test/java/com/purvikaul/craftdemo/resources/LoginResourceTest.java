package com.purvikaul.craftdemo.resources;

import com.purvikaul.craftdemo.CraftDemoApplication;
import com.purvikaul.craftdemo.CraftDemoConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientProperties;
import org.joda.time.Instant;
import org.junit.Before;
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
public class LoginResourceTest {
    @Rule
    public final DropwizardAppRule<CraftDemoConfiguration> RULE = new DropwizardAppRule<CraftDemoConfiguration>(CraftDemoApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

    String currentUsername;
    Client client;

    public static String generateUsername() {
        String username =  "auto"+ String.valueOf(Instant.now().getMillis());
        return username;
    }
    public static String signupQueryGenerator(String generatedUsername) {
        String json = "{\n" +
                "\t\"first_name\" : \"purvi\",\n" +
                "\t\"last_name\" : \"kaul\",\n" +
                "\t\"username\" : " + "\"" + generatedUsername + "\",\n" +
                "\t\"email\" : \"kaulp@uci.edu\",\n" +
                "\t\"password\" : \"12345\"\n" +
                "}";
        return json;
    }
    public static String loginQueryGenerator(String generatedUsername) {
        return "{\n"+
                " \"username\" :\"" + generatedUsername + "\",\n"+
                "\"password\" : \"12345\"\n"+"}";
    }
    public static String faultyLoginQueryGenerator(String generatedUsername) {
            return "{\n"+
                " \"username\" :\"" + generatedUsername + "\",\n"+
                "\"password\" : \"\"\n"+"}";
    }


    @Before
    public void signupUser() {
        client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
        client.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        client.property(ClientProperties.READ_TIMEOUT,    5000);
        currentUsername = generateUsername();
        Response response = client.target(
                String.format("http://localhost:%d/api/signup", RULE.getLocalPort()))
                .request()
                .post(Entity.entity(signupQueryGenerator(currentUsername), MediaType.APPLICATION_JSON));
        System.out.println(response.getStatusInfo().getReasonPhrase());
        assertThat(response.getStatus()).isEqualTo(200);
    }

    /** Test Signup endpoint **/
    @Test
    public void checkSignupEndpoint() {
        currentUsername = generateUsername();
        Response response = client.target(
                String.format("http://localhost:%d/api/signup", RULE.getLocalPort()))
                .request()
                .post(Entity.entity(signupQueryGenerator(currentUsername), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(200);

        response = client.target(
                String.format("http://localhost:%d/api/signup", RULE.getLocalPort()))
                .request()
                .post(Entity.entity(signupQueryGenerator(currentUsername), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    /** Test Login  endpoint **/
    @Test
    public void checkLoginEndpoint() {

        Response response = client.target(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort()))
                .request()
                .post(Entity.entity(loginQueryGenerator(currentUsername), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(200);

        response = client.target(
                String.format("http://localhost:%d/api/login", RULE.getLocalPort()))
                .request()
                .post(Entity.entity(faultyLoginQueryGenerator(currentUsername), MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
    }


}
