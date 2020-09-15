package com.example.auctionapp.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany
    private List<Role> roles;

    public User() {
    }

    public User(Long id, @NotBlank(message = "First name can't be blank") String firstName,
                @NotBlank(message = "Last name can't be blank") String lastName,
                @Email(message = "Email must be valid") String email,
                @NotBlank String password, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
