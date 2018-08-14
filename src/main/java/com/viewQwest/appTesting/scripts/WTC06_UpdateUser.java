package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.UpdateUserContext;
import com.viewQwest.appTesting.documents.UpdateUserPasswordDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC06_UpdateUser
 * DESCRIPTION : Find a user by user ID and update the user's password
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : WTC03_FindUserByEmail [ID of the user who's searched by Email]
 */

public class WTC06_UpdateUser {
    private String SERVICE_KEY;
    @Step("Step 11 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }
    @Features("API user testing")
    @Step("Step 12 : Fire the API request to UPDATE existing user [PUT Method]")
    @Title("[API TESTING] UPDATE existing user by password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search excising whale users user ID and UPDATE existing user by password. Check whether the giving results are according to correct json format")
    @Stories({"Search excising whale users user ID and UPDATE existing user by password"})
    @Test(dataProvider = "UserID", dataProviderClass = WTC03_FindUserByEmail.class)
    public void searchUserByFirstName(String userID) throws ConfigurationException {
        UpdateUserContext context = new UpdateUserContext();

        context.setUserID(userID);
        context.setPassword("44bc7b417faef5c8125581ebaf8b8e1e");

        //URL
        String URL = "/api/v1/user/update/";

        UpdateUserPasswordDocument apiBase = new UpdateUserPasswordDocument(context);
        String ALLURE_REPORT = apiBase.UpdateUserByChangingPassword(SERVICE_KEY, URL);
        performedActions(ALLURE_REPORT);
    }
    @Attachment
    private String performedActions(String actionSequence) {
        return "User details:" + actionSequence;
    }
}
