package de.lenic.serverservice.spigot.server;

import de.lenic.serverservice.spigot.ServerServiceApplication;
import de.lenic.serverservice.spigot.ServerServicePlugin;
import de.lenic.serverservice.spigot.config.ServerConfig;
import io.undertow.Undertow;
import jakarta.ws.rs.core.Application;
import org.jboss.resteasy.core.ResteasyDeploymentImpl;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

public class ServerManager {

    private ServerConfig config;

    private UndertowJaxrsServer server;


    public ServerManager(ServerConfig config) {
        this.config = config;
    }


    /**
     * Starts the server
     * @throws Exception Gets thrown when the server fails to startServer
     */
    public void startServer() throws Exception {
        server = new UndertowJaxrsServer();
        server.start(
                Undertow.builder().addHttpListener(
                        config.getAddress().getPort(),
                        config.getAddress().getHostString()
                )
        );

        deployApplication("/serverservice", new ServerServiceApplication());
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
     * Make a new deployment
     * @param application The {@link Application} to deploy
     * @return The {@link UndertowJaxrsServer} the deployment has been deployed to
     */
    public UndertowJaxrsServer deployApplication(Application application) {
        return deployApplication("/", application);
    }

    /**
     * Make a new deployment
     * @param contextPath The context path of the deployment
     * @param application The {@link Application} to deploy
     * @return The {@link UndertowJaxrsServer} the deployment has been deployed to
     */
    public UndertowJaxrsServer deployApplication(String contextPath, Application application) {
        var deployment = new ResteasyDeploymentImpl();
        deployment.setApplication(application);
        var undertowDeployment = server.undertowDeployment(deployment, buildFullContextPath(contextPath));
        return server.deploy(undertowDeployment);
    }


    /**
     * Convert a sub-path to a full path
     * @param partialPath The sub-path
     * @return The combined base-path and sub-path
     */
    private String buildFullContextPath(String partialPath) {
        String fullPath = "/api";

        if (partialPath.startsWith("/")) {
            fullPath += partialPath;
        } else {
            fullPath += "/" + partialPath;
        }

        return fullPath;
    }

}
