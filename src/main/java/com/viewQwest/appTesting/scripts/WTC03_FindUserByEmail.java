package com.viewQwest.appTesting.scripts;

import com.google.gson.Gson;
import com.viewQwest.appTesting.contexts.FindUserByEmailContext;
import com.viewQwest.appTesting.documents.FindUserByEmailDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import com.viewQwest.appTesting.service.WhaleUser;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.*;


/**
 * TEST CASE : WTC03_FindUserByEmail
 * DESCRIPTION : Verify created user
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : WTC02_CreateNewUser [email]
 */

public class WTC03_FindUserByEmail {
    private String SERVICE_KEY;
    private String ALLURE_REPORT;
    private String userDetails;

    @Step("Step 5 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }


    @Features("API user testing")
    @Step("Step 6 : Fire the API request to search a whale user by E-Mail [GET Method]")
    @Title("[API TESTING] Search excising whale user by E-Mail ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search excising whale user by E-Mail  - Fire the search user GET request and check where the user existing in the system and giving the results according to given json format")
    @Stories({"Search whale user by E-mail"})
    @Test(dataProvider = "UserData", dataProviderClass = WTC02_CreateNewUser.class)
    public void searchUserByEmail(String email, String firstName, String userLevel) throws ConfigurationException {
        FindUserByEmailContext context = new FindUserByEmailContext();
        context.setEmail(email); //cannot run as a single test case with Email hard dependency
//        context.setEmail("vqsmsubprimary@mailinator.com");

        //URL
        String URL = "/api/v1/user/find/by/email/";

        FindUserByEmailDocument apiBase = new FindUserByEmailDocument(context);
        ALLURE_REPORT = apiBase.findUserByEmail(SERVICE_KEY, URL);
        performedActions(ALLURE_REPORT);

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        String input = ALLURE_REPORT;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(config.getProperty("USER_JSON_OBJECT").toString().trim()));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment
    private String performedActions(String actionSequence) {
        return "User details:" + actionSequence;
    }

    @DataProvider(name = "UserID")
    public Object[][] dataProvider() throws ConfigurationException {

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.getProperty("USER_JSON_OBJECT").toString().trim()));
            userDetails = reader.readLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        WhaleUser userList = gson.fromJson(userDetails, WhaleUser.class);
        String UserID = userList.getId();
        Object[][] userData = {{UserID}};
        return userData;
    }
}
