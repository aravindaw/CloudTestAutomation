package com.viewQwest.appTesting.contexts;

public class UpdateHubContext implements Context {
    private String hubId;
    private String hubName;

    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }
}
