package com.viewQwest.appTesting.contexts;

public class FindUsersByFirstNameAndLevelContext implements Context{
    private String firstName;
    private String userLevel;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
}
