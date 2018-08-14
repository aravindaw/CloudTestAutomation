package com.viewQwest.appTesting.service;

public class WhaleUser {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String authToken;
    private String password;
    private String userLevel;
    private String parentId;
    private String childLevels;
    private String hubs;
    private String status;
    private String createdDate;
    private String lastUpdatedDate;

    public WhaleUser(String id, String firstName, String lastName, String email, String mobileNumber, String authToken, String password, String userLevel, String parentId, String childLevels, String hubs, String status, String createdDate, String lastUpdatedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.authToken = authToken;
        this.password = password;
        this.userLevel = userLevel;
        this.parentId = parentId;
        this.childLevels = childLevels;
        this.hubs = hubs;
        this.status = status;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildLevels() {
        return childLevels;
    }

    public void setChildLevels(String childLevels) {
        this.childLevels = childLevels;
    }

    public String getHubs() {
        return hubs;
    }

    public void setHubs(String hubs) {
        this.hubs = hubs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
