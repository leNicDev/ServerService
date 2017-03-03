package de.lenic.serverservice.spigot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.lenic.serverservice.spigot.auth.Role;
import de.lenic.serverservice.spigot.config.ConfigKey;
import de.lenic.serverservice.spigot.config.ServerConfig;
import de.lenic.serverservice.spigot.inject.ServerServiceModule;
import de.lenic.serverservice.spigot.server.ServerManager;
import de.lenic.serverservice.spigot.services.role.IRoleService;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerServicePlugin extends JavaPlugin {

    private static Injector injector;

    private ServerManager serverManager;


    @Override
    public void onEnable() {
        // Setup Guice injector
        injector = Guice.createInjector(new ServerServiceModule());

        // Create default configuration
        saveDefaultConfig();

        // Setup roles
        setupRoles();

        // Setup and start HTTP server
        setupServerManager();
    }

    @Override
    public void onDisable() {
        // Stop HTTP server if initialized
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


    private void setupRoles() {
        IRoleService roleService = injector.getInstance(IRoleService.class);

        // Load all roles from configuration
        getConfig().getConfigurationSection("roles").getKeys(false).forEach(name -> {
            Role role = new Role();

            role.setIdentifier(name);
            role.setToken(getConfig().getString("roles." + name + ".token"));
            role.setPermissions(getConfig().getStringList("roles." + name + ".permissions"));

            roleService.addRole(role);
        });

        getLogger().info("Loaded " + roleService.getRoles().size() + " role(s).");
    }


    /**
     * @return The {@link com.google.inject.Injector} of the ServerService plugin
     */
    public static Injector getInjector() {
        return injector;
    }

}
