package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.FindUsersByFirstNameAndLevelContext;
import com.viewQwest.appTesting.documents.FindUsersByFirstNameAndLevelDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC05_FindUsersByFirstNameAndLevel
 * DESCRIPTION : Find all users by First Name And Level
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : WTC02_CreateNewUser [First Name and user Level]
 */

public class WTC05_FindUsersByFirstNameAndLevel {
    private String SERVICE_KEY;

    @Step("Step 9 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("API user testing")
    @Step("Step 10 : Fire the API request to search a whale user/users by first name and user level[GET Method]")
    @Title("[API TESTING] Search excising whale user/users by first name and user level")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search excising whale users by first name and user level- Fire the search user GET request and check where the user/users by first name and user lever existing in the system and check whether the giving results are according to correct json format")
    @Stories({"Search whale user/users by First name and user level"})
    @Test(dataProvider = "UserData", dataProviderClass = WTC02_CreateNewUser.class)
    public void searchUserByFirstName(String email, String firstName, String userLevel) throws ConfigurationException {
        FindUsersByFirstNameAndLevelContext context = new FindUsersByFirstNameAndLevelContext();

        context.setFirstName(firstName);
        context.setUserLevel(userLevel);

        //URL
        String URL = "/api/v1/user/find/all/by/keyword/";

        FindUsersByFirstNameAndLevelDocument apiBase = new FindUsersByFirstNameAndLevelDocument(context);
        String ALLURE_REPORT = apiBase.findUsersByFirstNameAndLevel(SERVICE_KEY, URL);
        performedActions(ALLURE_REPORT);
    }
    @Attachment
    private String performedActions(String actionSequence) {
        return "User details:" + actionSequence;
    }
}
