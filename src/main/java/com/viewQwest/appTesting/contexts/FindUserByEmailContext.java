package com.viewQwest.appTesting.contexts;

public class FindUserByEmailContext {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FindUserByEmailContext() {
    }

    public FindUserByEmailContext(String email) {
        this.email = email;
    }
}
