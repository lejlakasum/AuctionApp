package com.example.auctionapp.dto;

import javax.validation.constraints.NotNull;

public class UpdateRoleRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

    public UpdateRoleRequestDto() {
    }

    public UpdateRoleRequestDto(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
