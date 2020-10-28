package com.example.auctionapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class UserDto extends BaseResourceDto {

    @NotBlank(message = "First name can't be blank")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password can't be blank")
    private String password;

    @NotBlank
    private String imageUrl;

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
                   String imageUrl) {
        super(id, dateCreated, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
