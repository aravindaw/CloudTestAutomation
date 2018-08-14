package com.viewQwest.appTesting.documents;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.viewQwest.appTesting.contexts.FindUserByFirstNameContext;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.Assert;

import static com.jayway.restassured.RestAssured.given;

public class FindUserByFirstNameDocument implements Document {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FindUserByFirstNameDocument.class);
    private final FindUserByFirstNameContext context;

    public FindUserByFirstNameDocument(FindUserByFirstNameContext context) {
        this.context = context;
    }

    public String findUserByFirstName(String SERVICE_KEY, String URL) throws ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));
        RestAssured.baseURI = config.getProperty("baseURI").toString().trim();

        Response response = given().
                when().
                header("url", config.getProperty("baseURI").toString().trim() + "/api").
                header("ticket", SERVICE_KEY).
                get(URL + context.getFirstName() + "/01").
                then().
                extract().
                response();
        log.info("==>[{}]", response.asString());
        Assert.assertEquals(response.statusCode(), 200);
        return response.asString();
    }
}
