package com.viewQwest.appTesting.service;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;

import java.io.*;

import static com.jayway.restassured.RestAssured.given;

public class ValidateServiceTicket {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateServiceTicket.class);
    private String serviceKey;

    public boolean validateServiceTicket() throws JSONException, ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        RestAssured.baseURI = config.getProperty("baseURI").toString().trim();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(config.getProperty("SERVICE_TICKET").toString().trim()));
            serviceKey = reader.readLine();
            log.info("[{}]", serviceKey);
            reader.close();
        } catch (IOException e) {
            log.info("[{}]","EXCEPTION ON SERVICE KEY...");
            return false;
        }
        Response response = given().
                keystore(config.getProperty("kEY_STORE_PATH").toString().trim(), config.getProperty("kEY_STORE_PASSWORD").toString().trim()).
                when().
                param("service", config.getProperty("baseURI").toString().trim()+"/api").
                param("ticket", serviceKey).
                header("Content-Type", "application/x-www-form-urlencoded").
                get("/cas/p3/serviceValidate").
                then().
                contentType(ContentType.HTML).
                extract().
                response();
        Assert.assertEquals(response.statusCode(), 200);
        return response.body().asString().contains("authenticationSuccess");

    }
}
