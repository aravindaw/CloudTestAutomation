package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.FindUserByFirstNameContext;
import com.viewQwest.appTesting.documents.FindUserByFirstNameDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC04_FindAllUsersByFirstName
 * DESCRIPTION : Find users by first name
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : WTC02_CreateNewUser [First Name]
 */
public class WTC04_FindAllUsersByFirstName {
    private String SERVICE_KEY;

    @Step("Step 7 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("API user testing")
    @Step("Step 8 : Fire the API request to search a whale user/users by first name[GET Method]")
    @Title("[API TESTING] Search excising whale user/users by first name")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search excising whale users by first name- Fire the search user GET request and check where the user/users existing in the system and giving the results according to given json format")
    @Stories({"Search whale user/users by First name"})
    @Test(dataProvider = "UserData", dataProviderClass = WTC02_CreateNewUser.class)
    public void searchUserByFirstName(String email, String firstName, String userLevel) throws ConfigurationException {
        FindUserByFirstNameContext context = new FindUserByFirstNameContext();

        context.setFirstName(firstName);

        //URL
        String URL = "/api/v1/user/find/all/by/keyword/";

        FindUserByFirstNameDocument apiBase = new FindUserByFirstNameDocument(context);
        String ALLURE_REPORT = apiBase.findUserByFirstName(SERVICE_KEY, URL);
        performedActions(ALLURE_REPORT);
    }


    @Attachment
    private String performedActions(String actionSequence) {
        return "User details:" + actionSequence;
    }
}
