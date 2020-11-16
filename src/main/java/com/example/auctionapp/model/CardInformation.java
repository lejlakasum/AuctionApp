package com.example.auctionapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "card_information")
public class CardInformation extends Resource {

    private String nameOnCard;

    private String cardNumber;

    private LocalDate cardExpiration;

    private String cvc;

    private Boolean paypal;

    private Boolean creditCard;

    public CardInformation() {
    }

    public CardInformation(String nameOnCard,
                           String cardNumber,
                           LocalDate cardExpiration,
                           String cvc,
                           Boolean paypal,
                           Boolean creditCard) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
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

    public LocalDate getCardExpiration() {
        return cardExpiration;
    }

    public void setCardExpiration(LocalDate cardExpiration) {
        this.cardExpiration = cardExpiration;
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
