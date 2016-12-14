package com.purvikaul.craftdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.purvikaul.craftdemo.request.DonationRequest;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


import javax.persistence.*;

/**
 * Created by purvi on 12/12/16.
 */
@Entity
@Table(name="donation")
@NamedQueries({
        @NamedQuery(name = "com.purvikaul.craftdemo.model.Donation.findAll",
                query = "select e from Donation e"),
        @NamedQuery(name = "com.purvikaul.craftdemo.model.Donation.findByUserId",
                query = "select e from Donation e "
                        + "where e.user.id = :userid ")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long d_id;

    private String item;

    @Column(name = "item_value")
    private Double value;
    private String category;
    private Double deductible;

    @Column(name = "time_stamp")
    private Long timestamp;

    @ManyToOne(cascade =CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "id",nullable = false)
    @JsonIgnore
    private User user;

    public Donation() {
    }

    public Donation(String item, Double value, String category, Double deductible, Long timestamp) {
        this.item = item;
        this.value = value;
        this.category = category;
        this.deductible = deductible;
        this.timestamp = timestamp;
    }
    public Donation(DonationRequest donationRequest,User user) {
        this.item = donationRequest.getItem();
        this.value = donationRequest.getValue();
        this.category = donationRequest.getCategory();
        this.deductible = donationRequest.getDeductible();
        this.timestamp = donationRequest.getTimestamp();
        this.user = user;
    }

    public long getD_id() {
        return d_id;
    }

    public void setD_id(long d_id) {
        this.d_id = d_id;
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty
    public String getItem() {
        return item;
    }

    @JsonProperty
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty
    public Double getValue() {
        return value;
    }

    @JsonProperty
    public void setValue(Double value) {
        this.value = value;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @JsonProperty
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty
    public Double getDeductible() {
        return deductible;
    }

    @JsonProperty
    public void setDeductible(Double deductible) {
        this.deductible = deductible;
    }

    @JsonProperty
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
