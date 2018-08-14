package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.UpdateHubContext;
import com.viewQwest.appTesting.documents.UpdateHubDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC12_UpdateHub
 * DESCRIPTION : Find Hub By hub id and change the hub name
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : WTC09_CreateHub[Hub Serial Number]
 */

public class WTC12_UpdateHub {
    private String SERVICE_KEY;

    @Step("Step 7 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("Hub API testing")
    @Step("Step 8 : Fire the API call to Find Hub By hub id and change the hub name[PUT Method]")
    @Title("[Hub API TESTING] Find Hub By hub id and change the hub name")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Find Hub By hub id and change the hub name - Fire the PUT request to Find Hub By hub id and change the hub name. Check where the giving results are according to correct json format")
    @Stories({"Find Hub By hub id and change the hub name"})
    @Test(dataProvider = "HubData", dataProviderClass = WTC09_CreateHub.class)
    public void findHubById(String hubId, String hubSerialNumber) throws ConfigurationException {

        UpdateHubContext context = new UpdateHubContext();
        context.setHubId(hubId);
        context.setHubName("Hub Name Changed");

        //URL
        String baseUrl = "/api/v1/hub/update/";

        UpdateHubDocument apiBase = new UpdateHubDocument(context);
        String ALLURE_REPORT = apiBase.updateHubApiBase(SERVICE_KEY, baseUrl);
        performedActions(ALLURE_REPORT);
    }


    @Attachment
    private String performedActions(String actionSequence) {
        return "Status :" + actionSequence;
    }
}
