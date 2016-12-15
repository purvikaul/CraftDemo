package com.purvikaul.craftdemo.resources;

import com.purvikaul.craftdemo.model.Donation;
import com.purvikaul.craftdemo.model.DonationDAO;
import com.purvikaul.craftdemo.model.User;
import com.purvikaul.craftdemo.model.UserDAO;
import com.purvikaul.craftdemo.request.DonationRequest;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by purvi on 12/12/16.
 */
@Path("/donations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DonationResource {

    private DonationDAO donationDAO;
    private UserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    public DonationResource(DonationDAO donationDAO, UserDAO userDAO) {
        this.donationDAO = donationDAO;
        this.userDAO = userDAO;
    }




    @POST
    @UnitOfWork
    public Response addDonations(DonationRequest donationRequest){
        LOGGER.info("Checking Donation Add Request");
        User user = userDAO.findByUserId(donationRequest.getUserid());
        if(user!= null){
            Donation donation = new Donation(donationRequest,user);
            donationDAO.insert(donation);
            return Response.status(200).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @UnitOfWork
    public Response getDonations(@QueryParam("userid") long userid) {
        LOGGER.info("Checking Donation Get Request");
        donationDAO.findByUserId(userid);
        if(donationDAO != null)
            return Response.ok(donationDAO).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
