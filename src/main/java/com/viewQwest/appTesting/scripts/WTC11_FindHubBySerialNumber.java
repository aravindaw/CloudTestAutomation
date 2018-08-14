package com.viewQwest.appTesting.scripts;

import com.viewQwest.appTesting.contexts.FindHubBySerialNumberContext;
import com.viewQwest.appTesting.documents.FindHubBySerialNumberDocument;
import com.viewQwest.appTesting.precondition.Precondition_CheckServiceKey;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;

/**
 * TEST CASE : WTC11_FindHubBySerialNumber
 * DESCRIPTION : Find Hub By Serial Number
 * SOFT DEPENDENCY : N/A
 * HARD DEPENDENCY : WTC09_CreateHub[Hub Serial Number]
 */

public class WTC11_FindHubBySerialNumber {
    private String SERVICE_KEY;

    @Step("Step 5 : Checking the Service KEY, If it's expired new key will be generated..")
    @BeforeTest
    public void checkServiceKey() throws IOException, ConfigurationException, JSONException {
        Precondition_CheckServiceKey checkServiceKey = new Precondition_CheckServiceKey();
        SERVICE_KEY = checkServiceKey.GetServiceTicket();
        performedActions(SERVICE_KEY);
    }

    @Features("Hub API testing")
    @Step("Step 6 : Fire the API call to Find Hub By Serial Number [GET Method]")
    @Title("[Hub API TESTING] Find Hub By Serial Number ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Find Hub By Serial Number - Fire the GET request to Find Hub By Serial Number and check where the giving results are according to correct json format")
    @Stories({"Find Hub By Serial Number"})
    @Test(dataProvider = "HubData", dataProviderClass = WTC09_CreateHub.class)
    public void findHubById(String hubId, String hubSerialNumber) throws ConfigurationException {

        FindHubBySerialNumberContext context = new FindHubBySerialNumberContext();
        context.setSerialNumber(hubSerialNumber);

        //URL
        String baseUrl = "/api/v1/hub/find/by/serial/";

        FindHubBySerialNumberDocument apiBase = new FindHubBySerialNumberDocument(context);
        String ALLURE_REPORT = apiBase.findHubBySerialNumberApiBase(SERVICE_KEY, baseUrl);
        performedActions(ALLURE_REPORT);
    }


    @Attachment
    private String performedActions(String actionSequence) {
        return "Status :" + actionSequence;
    }
}
