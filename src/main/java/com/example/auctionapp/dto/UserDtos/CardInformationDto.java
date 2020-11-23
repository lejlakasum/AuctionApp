package com.example.auctionapp.dto.UserDtos;

import com.example.auctionapp.dto.BaseResourceDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CardInformationDto extends BaseResourceDto {

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-]*$")
    private String nameOnCard;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}\\-[0-9]{4}$")
    private String cardNumber;

    @NotEmpty
    @Size(min = 4, max = 4)
    private String yearExpiration;

    @NotEmpty
    private String monthExpiration;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{3,4}$")
    private String cvc;

    @NotNull
    private Boolean paypal;

    @NotNull
    private Boolean creditCard;

    public CardInformationDto() {
    }

    public CardInformationDto(Long id,
                              LocalDateTime dateCreated,
                              LocalDateTime lastModifiedDate,
                              String nameOnCard,
                              String cardNumber,
                              String yearExpiration,
                              String monthExpiration,
                              String cvc,
                              Boolean paypal,
                              Boolean creditCard) {
        super(id, dateCreated, lastModifiedDate);
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
