package com.viewQwest.appTesting.contexts;

import com.viewQwest.appTesting.service.WhaleEnums;

public class GetAllUsersByUserLevelContext implements Context {
    private WhaleEnums.UserLevels userLevel;

    public WhaleEnums.UserLevels getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(WhaleEnums.UserLevels userLevel) {
        this.userLevel = userLevel;
    }
}
