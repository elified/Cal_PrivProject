package com.httpserver;

import com.httpserver.config.Configuration;
import com.httpserver.config.ConfigurationManager;
import com.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * Driver Class for the Http Server
 *
 */
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {


        LOGGER.info("Server starting...");

        try {
            ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/com/http.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using Port: {}", conf.getPort());
        LOGGER.info("Using WebRoot: {}", conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO handle later
        }
    }

}
