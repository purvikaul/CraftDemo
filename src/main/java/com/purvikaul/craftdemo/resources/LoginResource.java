package com.purvikaul.craftdemo.resources;

import com.purvikaul.craftdemo.model.User;
import com.purvikaul.craftdemo.model.UserDAO;
import com.purvikaul.craftdemo.response.LoginResponse;
import io.dropwizard.hibernate.UnitOfWork;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);


    private UserDAO userDAO;

    public LoginResource(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @POST
    @Path("/login")
    @UnitOfWork
    public LoginResponse login(User user) {
        LOGGER.info("Checking Login Request");
        User temp_user =  userDAO.findByUserName(user.getUsername());
        if(temp_user.getPassword().equals(user.getPassword())){
            LOGGER.info("User found");
            return new LoginResponse( UUID.randomUUID().toString(),temp_user.getId());
        }

        return new LoginResponse();

    }
    @POST
    @Path("/signup")
    @UnitOfWork
    public LoginResponse signup(User user) {
        LOGGER.info("Checking Sign-Up Request");
        User temp_user =  userDAO.findByUserName(user.getUsername());
        if(temp_user == null){
            LOGGER.info("Username doesn't already exist found");
            userDAO.insert(user);
            return new LoginResponse( UUID.randomUUID().toString(),user.getId());
        }

        return new LoginResponse();

    }

}
