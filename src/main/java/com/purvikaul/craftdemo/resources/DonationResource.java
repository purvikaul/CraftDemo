package com.purvikaul.craftdemo.resources;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.purvikaul.craftdemo.CraftDemoConfiguration;
import com.purvikaul.craftdemo.model.Donation;
import com.purvikaul.craftdemo.model.Donations;
import com.purvikaul.craftdemo.model.User;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by purvi on 12/12/16.
 */
@Path("/donations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DonationResource {
    private MongoClient mongo;
    private String dbname;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    public DonationResource(MongoClient mongo,CraftDemoConfiguration configuration) {
        this.mongo = mongo;
        this.dbname = configuration.getDb();
    }

    @POST
    public Response addDonations(Donation donation){
        LOGGER.info("Checking Donation Add Request");
        DB db = mongo.getDB(dbname);
        DBCollection collection = db.getCollection("User");
        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collection, User.class,
                String.class);
        DBCursor<User> cursor = coll.find().is("username",donation.getUsername());
        if(cursor.hasNext()){
            LOGGER.info("User found");
            DBCollection donations = db.getCollection("Donations");
            JacksonDBCollection<Donation, String> donationStringJacksonDBCollection = JacksonDBCollection.wrap(donations, Donation.class,
                    String.class);
            WriteResult<Donation, String> result = donationStringJacksonDBCollection.insert(donation);
            LOGGER.info("Donation added");
            return Response.status(200).build();
        }
        LOGGER.info("User not in Database");
        return Response.status(400).build();
    }

    @GET
    public Donations getDonations(@QueryParam("username") String username) {
        LOGGER.info("Checking Donation Get Request");
        DB db = mongo.getDB(dbname);
        DBCollection collection = db.getCollection("User");
        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collection, User.class,
                String.class);
        DBCursor<User> cursor = coll.find().is("username",username);
        if(cursor.hasNext()){
            LOGGER.info("User found");
            DBCollection donationcoll = db.getCollection("Donations");
            JacksonDBCollection<Donation, String> donationStringJacksonDBCollection = JacksonDBCollection.wrap(donationcoll, Donation.class,
                    String.class);
            DBCursor cursor1 = donationStringJacksonDBCollection.find().is("username",username);
            List<Donation> list = new ArrayList<Donation>();
            while(cursor1.hasNext()){
                list.add((Donation) cursor1.next());
            }
            return new Donations(list);
        }
        LOGGER.info("User not in Database");
        return new Donations();

    }

}
