package com.viewQwest.appTesting.contexts;

import com.viewQwest.appTesting.service.WhaleEnums;

public class CreateNewUserContext {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private WhaleEnums.UserLevels userLevel;
    private String parentId;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public WhaleEnums.UserLevels getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(WhaleEnums.UserLevels userLevel) {
        this.userLevel = userLevel;
    }


    public CreateNewUserContext() {
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

    public CreateNewUserContext(String firstName, String lastName, String email, String mobileNumber, WhaleEnums.UserLevels userLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.userLevel = userLevel;
    }
}
