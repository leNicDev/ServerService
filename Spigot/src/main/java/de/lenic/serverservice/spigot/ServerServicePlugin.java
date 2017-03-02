/*
 * (C) Copyright 2017 DCR Network (https://dcr.bz/).
 * 
 * All rights reserved to the copyright holder.
 */
package de.lenic.serverservice.spigot;

import de.lenic.serverservice.spigot.config.ConfigKey;
import de.lenic.serverservice.spigot.server.ServerManager;
import de.lenic.serverservice.spigot.server.config.ServerConfig;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerServicePlugin extends JavaPlugin {

    private ServerManager serverManager;


    @Override
    public void onEnable() {
        saveDefaultConfig();

        setupServerManager();
    }

    @Override
    public void onDisable() {
        if(serverManager != null) {
            try {
                serverManager.close();
                getLogger().info("Successfully stopped HTTP server!");
            } catch (Exception ex) {
                ex.printStackTrace();
                getLogger().severe("Failed to stop HTTP server!");
            }
        }
    }


    private void setupServerManager() {
        // Configure HTTP Server using plugin configuration
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setAddress(
                getConfig().getString(ConfigKey.SERVER_HOST),
                getConfig().getInt(ConfigKey.SERVER_PORT)
        );
        serverConfig.setContextPath(getConfig().getString(ConfigKey.SERVER_CONTEXT_PATH));
        serverConfig.setResources(getResourceClasses());

        // Initialize ServerManager
        serverManager = new ServerManager(serverConfig);

        // Start HTTP server
        try {
            serverManager.startServer();
            getLogger().info("Successfully started HTTP server!");
        } catch (Exception ex) {
            ex.printStackTrace();
            getLogger().severe("Failed to start HTTP server!");
        }
    }

    private Set<Class<?>> getResourceClasses() {
        // Find names of all classes in resource package
        final List<String> resourceClassNames = new FastClasspathScanner("de.lenic.serverservice.spigot.server.resources")
                .scan()
                .getNamesOfAllStandardClasses();

        // Find all resource classes
        Set<Class<?>> resourceClasses = new HashSet<>();

        resourceClassNames.forEach(className -> {
            try {
                Class<?> clazz = Class.forName(className);
                resourceClasses.add(clazz);
            } catch (ClassNotFoundException ex) {
                getLogger().warning("Could not find class " + className + ".");
            }
        });

        return resourceClasses;
    }

}
