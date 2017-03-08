package de.lenic.serverservice.spigot.server;

import de.lenic.serverservice.spigot.ServerServiceApplication;
import de.lenic.serverservice.spigot.config.ServerConfig;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

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
        configureServer();
        server.start(
                Undertow.builder().addHttpListener(
                        config.getAddress().getPort(),
                        config.getAddress().getHostString()
                )
        );
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
     * Creates an HTTP Server configured using a {@link ServerConfig}
     */
    private void configureServer() {
        server = new UndertowJaxrsServer();
        server.deploy(new ServerServiceApplication(), config.getContextPath());
    }

}
