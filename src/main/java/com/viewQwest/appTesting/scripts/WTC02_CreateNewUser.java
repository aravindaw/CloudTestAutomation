package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.CreateNewUserContext;
import com.viewQwest.appTesting.documents.CreateNewUserDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import com.viewQwest.appTesting.service.CreateServiceTicket;
import com.viewQwest.appTesting.service.WhaleEnums;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codehaus.jettison.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.*;
import java.util.Random;

/**
 * TEST CASE : WTC02_CreateNewUser
 * DESCRIPTION : Create New User in whale system
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : N/A
 */
public class WTC02_CreateNewUser {
    private String SERVICE_KEY;
    private String email;
    private String firstName;
    private String readUserDetails;
    private WhaleEnums.UserLevels userLevel;

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateServiceTicket.class);

    @BeforeSuite
    @Step("Step : [Before Suite] Create new User and generate user details")
    public void createUserID(ITestContext value) throws ConfigurationException {
        Random rand = new Random();
        int ranNumber = rand.nextInt(99999) + 10000;

        email = "uvqsmrt" + ranNumber + "@mailinator.com";
        value.setAttribute("userEmail", email);
        log.info("[{}]", value.getAttribute("userEmail"));

        firstName = "Kumara";
        value.setAttribute("firstName", firstName);
        log.info("[{}]", value.getAttribute("firstName"));

        userLevel = WhaleEnums.UserLevels.SUBSCRIBER_SECONDARY;
        value.setAttribute("userLevel", userLevel);
        log.info("[{}]", value.getAttribute("userLevel"));

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        String input = email.trim() + "-" + firstName.trim() + "-" + userLevel.toString();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(config.getProperty("NEW_USER_DETAILS").toString().trim()));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        performedActions("User ID : " + value.getAttribute("userEmail").toString());
        performedActions("First Name : " + value.getAttribute("firstName").toString());
    }

    @Step("Step 3 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }


    @Features("API user testing")
    @Step("Step 4 : Fire the API call for create user[POST Method]")
    @Title("[API TESTING] POST Create new whale users")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new users - Fire the user create user POST request and check where the user create without any errors")
    @Stories({"create new user"})
    @Test
    public void creteNewUser() throws ConfigurationException {

        CreateNewUserContext context = new CreateNewUserContext();

        //New user details
        context.setFirstName(firstName);
        context.setLastName("Perera");
        context.setEmail(email);
        context.setMobileNumber("+94123456789");
        context.setUserLevel(userLevel);
        context.setParentId("59d5e3b952cefe6f64d7f2a0");

        //URL
        String createNewUserURL = "/api/v1/user/create";

        CreateNewUserDocument apiBase = new CreateNewUserDocument(context);
        String ALLURE_REPORT = apiBase.createNewUsersApiBase(SERVICE_KEY, createNewUserURL);
        performedActions(ALLURE_REPORT);
    }


    @Attachment
    private String performedActions(String actionSequence) {
        return "Status :" + actionSequence;
    }

    @DataProvider(name = "UserData")
    public Object[][] dataProvider() throws ConfigurationException {
//        String ID = "vqsmsubprimary@mailinator.com";
//        String FirstName = "Nimal";

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.getProperty("NEW_USER_DETAILS").toString().trim()));
            readUserDetails = reader.readLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] userDetail = readUserDetails.split("-");

        String Email = userDetail[0];
        String FirstName = userDetail[1];
        String UserLevel = userDetail[2];

        Object[][] userData = {{Email, FirstName, UserLevel}};
        return userData;
    }
}
