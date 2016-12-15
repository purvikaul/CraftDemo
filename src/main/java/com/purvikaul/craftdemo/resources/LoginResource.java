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
import javax.ws.rs.core.Response;
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
    public Response login(User user) {
        LOGGER.info("Checking Login Request");
        User temp_user =  userDAO.findByUserName(user.getUsername());
        if(temp_user.getPassword().equals(user.getPassword())){
            LOGGER.info("User found");
            LoginResponse loginResponse =new LoginResponse( UUID.randomUUID().toString(),temp_user.getId());
            return Response.ok(loginResponse).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();

    }
    @POST
    @Path("/signup")
    @UnitOfWork
    public Response signup(User user) {
        LOGGER.info("Checking Sign-Up Request");
        User temp_user =  userDAO.findByUserName(user.getUsername());
        if(temp_user == null){
            LOGGER.info("Username doesn't already exist found");
            userDAO.insert(user);
            LoginResponse loginResponse =new LoginResponse( UUID.randomUUID().toString(),user.getId());
            return Response.ok(loginResponse).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();

    }

}
