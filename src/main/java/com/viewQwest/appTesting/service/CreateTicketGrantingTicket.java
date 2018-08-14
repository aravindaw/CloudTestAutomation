package com.viewQwest.appTesting.service;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;

class CreateTicketGrantingTicket {

    String createTicketGrantingTicket() throws JSONException, ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));
        RestAssured.baseURI = config.getProperty("baseURI").toString().trim();

        String userName = config.getProperty("CERT_CREATE_USER_NAME").toString().trim();
        String password = config.getProperty("CERT_CREATE_PASSWORD").toString().trim();

        Response response = given().
                keystore(config.getProperty("kEY_STORE_PATH").toString().trim(), config.getProperty("kEY_STORE_PASSWORD").toString().trim()).
                when().
                header("Content-Type", "application/x-www-form-urlencoded").
                body("username=" + userName + "&password=" + password).
                post("/cas/v1/tickets").
                then().
                contentType(ContentType.HTML).
                extract().
                response();
        Assert.assertEquals(response.statusCode(), 201);
        return response.header("Location");
    }
}
