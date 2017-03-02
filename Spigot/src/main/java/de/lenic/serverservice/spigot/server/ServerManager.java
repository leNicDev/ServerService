/*
 * (C) Copyright 2017 DCR Network (https://dcr.bz/).
 * 
 * All rights reserved to the copyright holder.
 */
package de.lenic.serverservice.spigot.server;

import de.lenic.serverservice.spigot.server.config.ServerConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerManager {

    private ServerConfig config;

    private Server server;


    public ServerManager(ServerConfig config) {
        this.config = config;
    }


    /**
     * Starts the server
     * @throws Exception Gets thrown when the server fails to startServer
     */
    public void startServer() throws Exception {
        this.server = configureServer();
        this.server.start();
    }

    /**
     * Stops the server
     * @throws Exception Gets thrown when the server fails to stop
     */
    public void close() throws Exception {
        if(this.server != null)
            this.server.stop();
    }


    /**
     * Creates an HTTP Server configured using a {@link de.lenic.serverservice.spigot.server.config.ServerConfig}
     * @return A configured instance of {@link org.eclipse.jetty.server.Server}
     */
    private Server configureServer() {
        ResourceConfig resourceConfig = new ResourceConfig(config.getResources());
        resourceConfig.register(JacksonFeature.class);

        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);

        Server server = new Server(config.getAddress());

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath(config.getContextPath());
        contextHandler.addServlet(servletHolder, config.getContextPath() + '*');

        server.setHandler(contextHandler);

        return server;
    }

}