package com.bodaboda.bodaboda.classes;

public class Token {
    private long userId;
    private String username;
    private String userType;
    private String token;

    public void Token(long userId, String username, String userType, String token){
        this.userId = userId;
        this.username = username;
        this.userType = userType;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
