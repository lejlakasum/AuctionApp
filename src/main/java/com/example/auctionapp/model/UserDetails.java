package com.example.auctionapp.model;

import com.example.auctionapp.enumeration.GenderEnum;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_details")
public class UserDetails extends Resource {

    private String phoneNumber;

    private LocalDateTime birthDate;

    private GenderEnum gender;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "card_info_id", referencedColumnName = "id")
    private CardInformation cardInformation;

    public UserDetails() {
    }

    public UserDetails(Address address, CardInformation cardInformation) {
        this.address = address;
        this.cardInformation = cardInformation;
    }

    public UserDetails(String phoneNumber,
                       LocalDateTime birthDate,
                       GenderEnum gender,
                       Address address,
                       CardInformation cardInformation) {
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.cardInformation = cardInformation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CardInformation getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CardInformation cardInformation) {
        this.cardInformation = cardInformation;
    }
}
