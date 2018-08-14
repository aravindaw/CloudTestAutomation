package com.viewQwest.appTesting.service;

import com.jayway.restassured.RestAssured;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetServiceTicket {
    public String serviceTicket() throws ConfigurationException, IOException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

        BufferedReader reader = new BufferedReader(new FileReader(config.getProperty("SERVICE_TICKET").toString().trim()));
        String serviceKey = reader.readLine();
        reader.close();

        return serviceKey;
    }
}
