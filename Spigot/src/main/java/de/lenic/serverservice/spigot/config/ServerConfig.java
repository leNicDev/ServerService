package de.lenic.serverservice.spigot.config;

import java.net.InetSocketAddress;

/**
 * Wrapper class for the server part in the plugin configuration
 */
public class ServerConfig {

    private InetSocketAddress address;


    public ServerConfig() {
        address = new InetSocketAddress("0.0.0.0", 8081);
    }


    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    public void setAddress(String host, int port) {
        this.address = new InetSocketAddress(host, port);
    }

}
