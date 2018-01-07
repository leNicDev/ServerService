package de.lenic.serverservice.spigot;

import de.lenic.serverservice.spigot.auth.Role;
import de.lenic.serverservice.spigot.config.ConfigKey;
import de.lenic.serverservice.spigot.config.ServerConfig;
import de.lenic.serverservice.spigot.server.ServerManager;
import de.lenic.serverservice.spigot.services.role.IRoleService;
import de.lenic.serverservice.spigot.services.role.RoleServiceImpl;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerServicePlugin extends JavaPlugin {

    private static ServerServicePlugin pluginInstance;

    private ServerManager serverManager;
    private IRoleService roleService;


    @Override
    public void onEnable() {
        pluginInstance = this;

        // Create default configuration
        saveDefaultConfig();

        // Setup role service
        roleService = new RoleServiceImpl();

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


    private void setupRoles() {
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
     * @return The only instance of the ServerService plugin
     */
    public static ServerServicePlugin getInstance() {
        return pluginInstance;
    }


    public ServerManager getServerManager() {
        return serverManager;
    }

    public IRoleService getRoleService() {
        return roleService;
    }

}
