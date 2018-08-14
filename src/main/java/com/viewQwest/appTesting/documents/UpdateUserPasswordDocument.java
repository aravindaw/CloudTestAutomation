package com.viewQwest.appTesting.documents;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.viewQwest.appTesting.contexts.UpdateUserContext;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;

public class UpdateUserPasswordDocument implements Document {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UpdateUserPasswordDocument.class);
    private final UpdateUserContext context;

    public UpdateUserPasswordDocument(UpdateUserContext context) {
        this.context = context;
    }

    public String UpdateUserByChangingPassword(String SERVICE_KEY, String URL) throws ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));
        RestAssured.baseURI = config.getProperty("baseURI").toString().trim();

        JSONObject json = new JSONObject();
        json.put("password", context.getPassword());

        Response response = given().
                when().
                header("content-Type", "application/json").
                header("url", config.getProperty("baseURI").toString().trim() + "/api").
                header("ticket", SERVICE_KEY).
                body(json).
                put(URL + context.getUserID()).
                then().
//                contentType(ContentType.JSON).
                extract().
                response();
        log.info(response.asString());
        Assert.assertEquals(response.statusCode(), 200);
        return response.asString();
    }
}
