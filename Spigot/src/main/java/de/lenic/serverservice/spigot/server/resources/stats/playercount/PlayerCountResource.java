package de.lenic.serverservice.spigot.server.resources.stats.playercount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.lenic.serverservice.spigot.ServerServicePlugin;
import de.lenic.serverservice.spigot.config.annotations.PermissionRequired;
import de.lenic.serverservice.spigot.server.resources.ResourcePermission;
import de.lenic.serverservice.spigot.services.stats.IStatService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/stats/playercount")
public class PlayerCountResource {

    private IStatService statService;


    public PlayerCountResource() {
        statService = ServerServicePlugin.getInjector().getInstance(IStatService.class);
    }


    @GET
    @Path("/current")
    @Produces("application/json")
    @PermissionRequired(ResourcePermission.STATS_PLAYERCOUNT_CURRENT)
    public String currentPlayerCount() throws JsonProcessingException {
        final ObjectMapper objectMapper = ServerServicePlugin.getInjector().getInstance(ObjectMapper.class);

        return objectMapper.writeValueAsString(
                statService.getCurrentPlayerCount()
        );
    }

    @GET
    @Path("/max")
    @Produces("application/json")
    @PermissionRequired(ResourcePermission.STATS_PLAYERCOUNT_MAX)
    public String maxPlayerCount() throws JsonProcessingException {
        final ObjectMapper objectMapper = ServerServicePlugin.getInjector().getInstance(ObjectMapper.class);

        return objectMapper.writeValueAsString(
                statService.getMaxPlayerCount()
        );
    }

}
