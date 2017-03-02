/*
 * (C) Copyright 2017 DCR Network (https://dcr.bz/).
 * 
 * All rights reserved to the copyright holder.
 */
package de.lenic.serverservice.spigot.server.config;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class ServerConfig {

    private InetSocketAddress address;
    private String contextPath;
    private Set<Class<?>> resources;


    public ServerConfig() {
        address = new InetSocketAddress("0.0.0.0", 8081);
        contextPath = "/";
        resources = new HashSet<>();
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


    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }


    public Set<Class<?>> getResources() {
        return resources;
    }

    public void setResources(Set<Class<?>> resources) {
        this.resources = resources;
    }

    public boolean addResource(Class<?> resource) {
        return this.resources.add(resource);
    }

    public boolean removeResource(Class<?> resource) {
        return this.resources.remove(resource);
    }

    public boolean hasResource(Class<?> resource) {
        return this.resources.contains(resource);
    }

}
