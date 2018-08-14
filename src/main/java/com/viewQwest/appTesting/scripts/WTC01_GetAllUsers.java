package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.documents.GetAllUsersDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC01_GetAllUsers
 * DESCRIPTION : Get all users in whale users (page 01)
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : N/A
 */

public class WTC01_GetAllUsers {

    private String SERVICE_KEY;

    @Step("Step 1 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }


    @Features("API user testing")
    @Step("Step 2 : Fire the API call [GET Method]")
    @Title("[API TESTING] Get all whale users")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get all users in page 01 - Fire the user GET request and check where the users are getting without any errors according to the given JSON format")
    @Stories({"Get all users by pages"})
    @Test
    public void getAllUsers() throws ConfigurationException {
        String getAllUsers = "/api/v1/user/find/all/01";
        GetAllUsersDocument apiBase = new GetAllUsersDocument();
        String ALLURE_REPORT = apiBase.getUsersApiBase(SERVICE_KEY, getAllUsers);
        performedActions(ALLURE_REPORT);
    }

    @Attachment
    private String performedActions(String actionSequence) {
        return "Service Key :"+ actionSequence;
    }

}
