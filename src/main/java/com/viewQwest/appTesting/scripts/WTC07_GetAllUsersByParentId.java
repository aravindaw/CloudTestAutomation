package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.GetAllUsersByParentIdContext;
import com.viewQwest.appTesting.documents.GetAllUsersByParentIdDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC07_GetAllUsersByParentId
 * DESCRIPTION : Get all users by parent Id
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : N/A
 */

public class WTC07_GetAllUsersByParentId {
    private String SERVICE_KEY;
    @Step("Step 13 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("API user testing")
    @Step("Step 14 : Fire the API request to get all users by Parent Id [GET Method]")
    @Title("[API TESTING] Get all users by Parent Id ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search excising whale users by Parent Id . Check whether the giving results are according to correct json format")
    @Stories({"Search excising whale users by Parent Id "})
    @Test
    public void searchUserByFirstName() throws ConfigurationException {
        GetAllUsersByParentIdContext context = new GetAllUsersByParentIdContext();

        context.setParentID("59d5e3b952cefe6f64d7f2a0");

        //URL
        String URL = "/api/v1/user/find/all/by/parent/";

        GetAllUsersByParentIdDocument apiBase = new GetAllUsersByParentIdDocument(context);
        String ALLURE_REPORT = apiBase.getAllUsersByParentId(SERVICE_KEY, URL);
        performedActions(ALLURE_REPORT);
    }

    @Attachment
    private String performedActions(String actionSequence) {
        return "User details:" + actionSequence;
    }
}
