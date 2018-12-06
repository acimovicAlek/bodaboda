package com.bodaboda.bodaboda.classes;

public class Account {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private boolean isDriver;
    private String token;
    private Location currentLoc;

    public void Account(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Location getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(Location currentLoc) {
        this.currentLoc = currentLoc;
    }
}
