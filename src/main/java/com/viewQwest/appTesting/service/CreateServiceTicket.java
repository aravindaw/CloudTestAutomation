package com.viewQwest.appTesting.service;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codehaus.jettison.json.JSONException;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class CreateServiceTicket {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateServiceTicket.class);

    public void createServiceTicket() throws JSONException, ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        RestAssured.baseURI = config.getProperty("baseURI").toString().trim();
        CreateTicketGrantingTicket ticket = new CreateTicketGrantingTicket();
        String[] URL = ticket.createTicketGrantingTicket().split("cas");
        log.info(URL[1]);

        Response response = given().
                keystore(config.getProperty("kEY_STORE_PATH").toString().trim(), config.getProperty("kEY_STORE_PASSWORD").toString().trim()).
                when().
                param("service", config.getProperty("baseURI").toString().trim() + "/api").
                header("Content-Type", "application/x-www-form-urlencoded").
                post("/cas" + URL[1]).
                then().
                contentType(ContentType.TEXT).
                extract().
                response();
        Assert.assertEquals(response.statusCode(), 200);
        log.info("[{}]", response.asString());

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(config.getProperty("SERVICE_TICKET").toString().trim()));
            writer.write(response.asString());
            writer.close();
        } catch (IOException e) {
            log.info(e);
        }
    }
}
