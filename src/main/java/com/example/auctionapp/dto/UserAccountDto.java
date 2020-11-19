package com.example.auctionapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserAccountDto extends BaseResourceDto {

    @NotNull
    private UserRegisterDto userRegister;

    private UserDetailsDto userDetails;

    public UserAccountDto(Long id,
                          LocalDateTime dateCreated,
                          LocalDateTime lastModifiedDate,
                          UserRegisterDto userRegister,
                          UserDetailsDto userDetails) {
        super(id, dateCreated, lastModifiedDate);
        this.userRegister = userRegister;
        this.userDetails = userDetails;
    }

    public UserRegisterDto getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegisterDto userRegister) {
        this.userRegister = userRegister;
    }

    public UserDetailsDto getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsDto userDetails) {
        this.userDetails = userDetails;
    }
}
