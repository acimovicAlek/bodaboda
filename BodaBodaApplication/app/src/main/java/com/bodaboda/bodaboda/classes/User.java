package com.bodaboda.bodaboda.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    private long UserId;

    @SerializedName("username")
    private String Username;

    @SerializedName("password")
    private String Password;

    @SerializedName("userType")
    private String UserType;

    @SerializedName("firstName")
    private String FirstName;

    @SerializedName("lastName")
    private String LastName;

    @SerializedName("email")
    private String Email;

    @SerializedName("phoneNumber")
    private String PhoneNumber;

    @SerializedName("taxiPrice")@Expose
    private TaxiPrice taxiPrice;


    public User(String username, String password, String userType, String firstName, String lastName, String email, String phoneNumber) {
        this.Username = username;
        this.Password = password;
        this.UserType = userType;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public TaxiPrice getTaxiPrice() {
        return taxiPrice;
    }

    public void setTaxiPrice(TaxiPrice taxiPrice) {
        this.taxiPrice = taxiPrice;
    }
}
