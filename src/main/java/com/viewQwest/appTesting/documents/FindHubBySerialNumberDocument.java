package com.viewQwest.appTesting.documents;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.viewQwest.appTesting.contexts.FindHubBySerialNumberContext;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;

public class FindHubBySerialNumberDocument {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FindHubBySerialNumberDocument.class);
    private final FindHubBySerialNumberContext context;

    public FindHubBySerialNumberDocument(FindHubBySerialNumberContext context) {
        this.context=context;
    }

    public String findHubBySerialNumberApiBase(String SERVICE_KEY, String URL) throws ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));
        RestAssured.baseURI = config.getProperty("baseURI").toString().trim();

        Response response = given().
                when().
                header("content-Type", "application/json").
                header("url", config.getProperty("baseURI").toString().trim() + "/api").
                header("ticket", SERVICE_KEY).
                get(URL+context.getSerialNumber()).
                then().
                extract().
                response();
        log.info(response.asString());
        Assert.assertEquals(response.statusCode(), 200);
        return response.asString();
    }
}
