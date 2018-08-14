package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.GetAllUsersByUserLevelContext;
import com.viewQwest.appTesting.documents.GetAllUsersByUserLevelDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import com.viewQwest.appTesting.service.WhaleEnums;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC08_GetAllUsersByUserLevel
 * DESCRIPTION : Get all users by parent Id
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : N/A
 */

public class WTC08_GetAllUsersByUserLevel {
    private String SERVICE_KEY;

    @Step("Step 15 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("API user testing")
    @Step("Step 16 : Fire the API request to get all users by User Level [GET Method]")
    @Title("[API TESTING] Get all users by User Level ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search excising whale users by User Level . Check whether the giving results are according to correct json format")
    @Stories({"Search excising whale users by User Level "})
    @Test
    public void searchUserByFirstName() throws ConfigurationException {
        GetAllUsersByUserLevelContext context = new GetAllUsersByUserLevelContext();

        context.setUserLevel(WhaleEnums.UserLevels.SUBSCRIBER_SECONDARY);

        //URL
        String URL = "/api/v1/user/find/all/by/level/";

        GetAllUsersByUserLevelDocument apiBase = new GetAllUsersByUserLevelDocument(context);
        String ALLURE_REPORT = apiBase.getAllUsersByUserLevel(SERVICE_KEY, URL);
        performedActions(ALLURE_REPORT);
    }

    @Attachment
    private String performedActions(String actionSequence) {
        return "User details:" + actionSequence;
    }

}
