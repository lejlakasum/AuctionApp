package com.example.auctionapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_account")
public class UserAccount extends Resource {

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_login_id", referencedColumnName = "id")
    private UserRegisterInformation userRegisterInformation;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    public UserAccount() {
    }

    public UserAccount(UserRegisterInformation userRegisterInformation, UserDetails userDetails) {
        this.userRegisterInformation = userRegisterInformation;
        this.userDetails = userDetails;
    }

    public UserRegisterInformation getUserLoginInformation() {
        return userRegisterInformation;
    }

    public void setUserLoginInformation(UserRegisterInformation userRegisterInformation) {
        this.userRegisterInformation = userRegisterInformation;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
