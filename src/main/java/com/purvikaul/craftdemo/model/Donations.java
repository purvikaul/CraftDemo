package com.purvikaul.craftdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by purvi on 12/12/16.
 */
public class Donations {
    @JsonProperty
    private List<Donation> donations;

    public Donations() {
    }

    public Donations(List<Donation> donations) {
        this.donations = donations;
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
