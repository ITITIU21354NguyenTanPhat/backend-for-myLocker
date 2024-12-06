package com.myApp.backend.dto;

public class LoginResponse {
    private boolean isValidUser;

    public LoginResponse(boolean isValidUser) {
        this.isValidUser = isValidUser;
    }

    public boolean isValidUser() {
        return isValidUser;
    }

    public void setValidUser(boolean validUser) {
        isValidUser = validUser;
    }
}
