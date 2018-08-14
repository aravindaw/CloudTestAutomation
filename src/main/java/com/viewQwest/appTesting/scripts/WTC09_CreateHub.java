package com.viewQwest.appTesting.scripts;

import com.google.gson.Gson;
import com.viewQwest.appTesting.contexts.CreateNewHubContext;
import com.viewQwest.appTesting.documents.CreateNewHubDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import com.viewQwest.appTesting.service.HubJsonObject;
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
import java.sql.Timestamp;

/**
 * TEST CASE : WTC09_CreateHub
 * DESCRIPTION : Create new hub
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : N/A
 */

public class WTC09_CreateHub {

    private String SERVICE_KEY;
    private String readHubDetails;

    @Step("Step 1 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("Hub API testing")
    @Step("Step 2 : Fire the API call to create Hub[POST Method]")
    @Title("[Hub API TESTING] POST Create new hub")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new hub - Fire the hub create user POST request to create new hub and check where the giving results are according to correct json format")
    @Stories({"create new hub"})
    @Test
    public void creteNewHub() throws ConfigurationException {

        CreateNewHubContext context = new CreateNewHubContext();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        context.setHubName("TestHub_" + timestamp.getTime());
        context.setSerialNumber("000" + timestamp.getTime());
        context.setUserId("59d5e3b952cefe6f64d7f2a0");

        //URL
        String baseUrl = "/api/v1/hub/create/";

        CreateNewHubDocument apiBase = new CreateNewHubDocument(context);
        String ALLURE_REPORT = apiBase.createNewHubApiBase(SERVICE_KEY, baseUrl);
        performedActions("New Hub Created...[" + ALLURE_REPORT + "]");

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));
        String input = ALLURE_REPORT;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(config.getProperty("NEW_HUB_JSON").toString().trim()));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Attachment
    private String performedActions(String actionSequence) {
        return "Status :" + actionSequence;
    }

    @DataProvider(name = "HubData")
    public Object[][] dataProvider() throws ConfigurationException {

        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.getProperty("NEW_HUB_JSON").toString().trim()));
            readHubDetails = reader.readLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        HubJsonObject hub = gson.fromJson(readHubDetails, HubJsonObject.class);

        String hubId = hub.getId();
        String hubSerialNumber = hub.getSerialNumber();

        Object[][] userData = {{hubId,hubSerialNumber}};
        return userData;
    }

}
