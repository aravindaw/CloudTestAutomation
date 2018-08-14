package com.viewQwest.appTesting.documents;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.viewQwest.appTesting.contexts.CreateNewHubContext;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;

public class CreateNewHubDocument implements Document {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateNewHubDocument.class);

    private final CreateNewHubContext context;

    public CreateNewHubDocument(CreateNewHubContext context) {
        this.context = context;
    }

    public String createNewHubApiBase(String SERVICE_KEY, String URL) throws ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));
        RestAssured.baseURI = config.getProperty("baseURI").toString().trim();

        JSONObject json = new JSONObject();
        json.put("name", context.getHubName());
        json.put("serialNumber", context.getSerialNumber());

        Response response = given().
                when().
                header("content-Type", "application/json").
                header("url", config.getProperty("baseURI").toString().trim() + "/api").
                header("ticket", SERVICE_KEY).
                body(json).
                post(URL+context.getUserId()).
                then().
                extract().
                response();
        Assert.assertEquals(response.statusCode(), 200);
        log.info("===>[{}]", json.toString());
        return json.toString();
    }
}
