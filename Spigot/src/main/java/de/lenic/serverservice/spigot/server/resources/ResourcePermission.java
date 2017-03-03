package de.lenic.serverservice.spigot.server.resources;

/**
 * Interface that contains constants for resource permissions.
 * The permissions are applied to the resources using the {@link de.lenic.serverservice.spigot.config.annotations.PermissionRequired} annotation.
 */
public interface ResourcePermission {

    String STATS_PLAYERCOUNT_CURRENT = "stats.playercount.current";
    String STATS_PLAYERCOUNT_MAX = "stats.playercount.max";

    String STATS_MOTD_PLAIN = "stats.motd.plain";
    String STATS_MOTD_MC = "stats.motd.minecraft";

}
