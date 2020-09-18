package com.example.auctionapp.dto;

import java.time.LocalDateTime;

public class UserDto extends BaseResourceDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long roleId;

    public UserDto() {
    }

    public UserDto(Long id,
                   LocalDateTime dateCreated,
                   LocalDateTime lastModifiedDate,
                   String firstName,
                   String lastName, String email,
                   String password,
                   Long roleId) {
        super(id, dateCreated, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public UserDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, String firstName, String lastName, String email, Long roleId) {
        super(id, dateCreated, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roleId = roleId;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
