package com.purvikaul.craftdemo.resources;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.purvikaul.craftdemo.CraftDemoConfiguration;
import com.purvikaul.craftdemo.model.User;
import com.purvikaul.craftdemo.response.LoginResponse;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    private MongoClient mongo;
    private String dbname;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    public LoginResource(MongoClient mongo,CraftDemoConfiguration configuration) {
        this.mongo = mongo;
        this.dbname = configuration.getDb();
    }

    @POST
    @Path("/login")
    public LoginResponse login(User user) {
        LOGGER.info("Checking Login Request");
        DB db = mongo.getDB(dbname);
        DBCollection collection = db.getCollection("User");
        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collection, User.class,
                String.class);
        DBCursor<User> cursor = coll.find().is("username",user.getUsername());
        if(cursor.hasNext()){
            LOGGER.info("User found");
            User user1 = cursor.next();
            if(user1.getPassword().equals(user.getPassword())){
                return new LoginResponse( UUID.randomUUID().toString(),user.getUsername());
            }
        }
        return new LoginResponse();

    }
    @POST
    @Path("/signup")
    public LoginResponse signup(User user) {
        LOGGER.info("Checking Sign-Up Request");
        System.out.println("Checking Sign-Up Request");
        DB db = mongo.getDB(dbname);
        DBCollection collection = db.getCollection("User");
        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collection, User.class,
                String.class);
        DBCursor<User> cursor = coll.find().is("username",user.getUsername());
        if(!cursor.hasNext()) {
            LOGGER.info("Username doesn't already exist");
            WriteResult<User, String> result = coll.insert(user);
            LOGGER.info("User is added");
            return new LoginResponse(UUID.randomUUID().toString(),user.getUsername());
        }
        return new LoginResponse();

    }

}
