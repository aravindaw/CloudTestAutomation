package com.viewQwest.appTesting.contexts;

public class GetAllUsersByParentIdContext implements Context{
    private String parentID;

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }
}
