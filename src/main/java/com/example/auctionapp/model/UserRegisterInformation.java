package com.example.auctionapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_login")
public class UserRegisterInformation extends Resource {
    @NotBlank(message = "First name can't be blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", updatable = false)
    private Image image;



    public UserRegisterInformation() {
    }

    public UserRegisterInformation(String firstName, String lastName, String email, String password, Role role, Image image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.image = image;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
