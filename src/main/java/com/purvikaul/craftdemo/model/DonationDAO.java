package com.purvikaul.craftdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by purvi on 12/12/16.
 */
public class DonationDAO extends AbstractDAO<Donation> {

    @JsonProperty
    private List<Donation> donations;


    public DonationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Donation> findAll(){
        donations = list(namedQuery("com.purvikaul.craftdemo.model.Donation.findAll"));
        return donations;
    }

    public List<Donation> findByUserId(long userid){
        donations = list(namedQuery("com.purvikaul.craftdemo.model.Donation.findByUserId").setParameter("userid",userid));
        System.out.println(donations.size());
        return donations;
    }

    public void insert(Donation donation){
        currentSession().persist(donation);
    }

    @JsonProperty
    public List<Donation> getDonations() {
        return donations;
    }

    @JsonProperty
    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
}
