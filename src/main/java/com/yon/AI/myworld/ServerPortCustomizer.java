package com.yon.AI.myworld;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;
@Component
public class ServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        // Fetching the environment variable
        String port = System.getenv("X_ZOHO_CATALYST_LISTEN_PORT");
        int listenPort;
        if (port != null && !port.isEmpty()) {
            listenPort = Integer.parseInt(port);  // Parse the port if it's available
        } else {
            listenPort = 9002;  // Default port
        }
        System.out.println("port ::: "+listenPort);
        factory.setPort(listenPort);  // Set the port
    }

}
