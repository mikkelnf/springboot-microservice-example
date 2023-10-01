package com.mnf.user.dto;

import java.time.LocalDate;

public class GetUserResponseDto {
    private String id;
    private String username;
    private Integer isLogin;
    private Integer isActive;
    private LocalDate createdDate;

    public GetUserResponseDto() {
    }

    public GetUserResponseDto(String id, String username, Integer isLogin, Integer isActive, LocalDate createdDate) {
        this.id = id;
        this.username = username;
        this.isLogin = isLogin;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
