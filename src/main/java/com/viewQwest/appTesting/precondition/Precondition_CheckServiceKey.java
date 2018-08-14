package com.viewQwest.appTesting.precondition;

import com.viewQwest.appTesting.service.CreateServiceTicket;
import com.viewQwest.appTesting.service.GetServiceTicket;
import com.viewQwest.appTesting.service.ValidateServiceTicket;
import org.apache.commons.configuration.ConfigurationException;
import org.codehaus.jettison.json.JSONException;

import java.io.IOException;

public class Precondition_CheckServiceKey {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Precondition_CheckServiceKey.class);

    public String GetServiceTicket() throws JSONException, ConfigurationException, IOException {
        CreateServiceTicket create = new CreateServiceTicket();
        ValidateServiceTicket validate = new ValidateServiceTicket();
        GetServiceTicket serviceTicket = new GetServiceTicket();

        if (!validate.validateServiceTicket()) {
            create.createServiceTicket();
            log.info("[{}]", serviceTicket.serviceTicket());
            return serviceTicket.serviceTicket();
        } else {
            log.info("[{}]", serviceTicket.serviceTicket());
            return serviceTicket.serviceTicket();
        }

    }
}
