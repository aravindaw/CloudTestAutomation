package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.FindHubByIdContext;
import com.viewQwest.appTesting.documents.FindHubByIdDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC10_FindHubById
 * DESCRIPTION : Find Hub By Id
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : WTC09_CreateHub[Hub Id]
 */

public class WTC10_FindHubById {
    private String SERVICE_KEY;

    @Step("Step 3 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("Hub API testing")
    @Step("Step 4 : Fire the API call to Find hub by Id[GET Method]")
    @Title("[Hub API TESTING] Find hub by Id")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Find hub by Id - Fire the find hub by Id GET request to find the hub and check where the giving results are according to correct json format")
    @Stories({"Find hub by Id"})
    @Test(dataProvider = "HubData", dataProviderClass = WTC09_CreateHub.class)
    public void findHubById(String hubId, String hubSerialNumber) throws ConfigurationException {

        FindHubByIdContext context = new FindHubByIdContext();
        context.setHubId(hubId);

        //URL
        String baseUrl = "/api/v1/hub/find/by/id/";

        FindHubByIdDocument apiBase = new FindHubByIdDocument(context);
        String ALLURE_REPORT = apiBase.findHubByIdApiBase(SERVICE_KEY, baseUrl);
        performedActions(ALLURE_REPORT);
    }


    @Attachment
    private String performedActions(String actionSequence) {
        return "Status :" + actionSequence;
    }
}
