package com.example.auctionapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "card_information")
public class CardInformation extends Resource {

    private String nameOnCard;

    private String cardNumber;

    private String yearExpiration;

    private String monthExpiration;

    private String cvc;

    private Boolean paypal;

    private Boolean creditCard;

    public CardInformation() {
    }

    public CardInformation(String nameOnCard,
                           String cardNumber,
                           String yearExpiration,
                           String monthExpiration,
                           String cvc,
                           Boolean paypal,
                           Boolean creditCard) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.yearExpiration = yearExpiration;
        this.monthExpiration = monthExpiration;
        this.cvc = cvc;
        this.paypal = paypal;
        this.creditCard = creditCard;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getYearExpiration() {
        return yearExpiration;
    }

    public void setYearExpiration(String yearExpiration) {
        this.yearExpiration = yearExpiration;
    }

    public String getMonthExpiration() {
        return monthExpiration;
    }

    public void setMonthExpiration(String monthExpiration) {
        this.monthExpiration = monthExpiration;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Boolean getPaypal() {
        return paypal;
    }

    public void setPaypal(Boolean paypal) {
        this.paypal = paypal;
    }

    public Boolean getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Boolean creditCard) {
        this.creditCard = creditCard;
    }
}
