package com.viewQwest.appTesting.contexts;

public class FindUserByFirstNameContext implements Context{
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public FindUserByFirstNameContext() {
    }
}
