package com.example.auctionapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class UserDto extends BaseResourceDto {

    @NotBlank(message = "First name can't be blank")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password can't be blank")
    private String password;

    private List<BidDto> bids;

    public UserDto() {
    }

    public UserDto(Long id,
                   LocalDateTime dateCreated,
                   LocalDateTime lastModifiedDate,
                   String firstName,
                   String lastName, String email,
                   String password) {
        super(id, dateCreated, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDto(Long id,
                   LocalDateTime dateCreated,
                   LocalDateTime lastModifiedDate,
                   String firstName,
                   String lastName,
                   String email,
                   Long roleId,
                   List<BidDto> bids) {
        super(id, dateCreated, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bids = bids;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BidDto> getBids() {
        return bids;
    }
}
