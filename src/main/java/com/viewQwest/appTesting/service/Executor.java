package com.viewQwest.appTesting.service;

import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;

public class Executor {
    public static void main(String[] args) throws JSONException, ConfigurationException {

        CreateServiceTicket ST = new CreateServiceTicket();
        ST.createServiceTicket();

        ValidateServiceTicket VST = new ValidateServiceTicket();
        try {
            VST.validateServiceTicket();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
