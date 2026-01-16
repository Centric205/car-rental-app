package com.carrental.api.user.dto;

import java.time.Instant;

public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private Instant createdAt;

    public UserResponse(){}

    public UserResponse(String fullName, String email, Instant createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
