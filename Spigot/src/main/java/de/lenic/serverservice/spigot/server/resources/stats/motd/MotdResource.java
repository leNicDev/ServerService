package de.lenic.serverservice.spigot.server.resources.stats.motd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.lenic.serverservice.spigot.ServerServicePlugin;
import de.lenic.serverservice.spigot.config.annotations.PermissionRequired;
import de.lenic.serverservice.spigot.server.resources.ResourcePermission;
import de.lenic.serverservice.spigot.services.stats.IStatService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/stats/motd")
public class MotdResource {

    private IStatService statService;


    public MotdResource() {
        statService = ServerServicePlugin.getInjector().getInstance(IStatService.class);
    }


    @GET
    @Path("/plain")
    @Produces("application/json")
    @PermissionRequired(ResourcePermission.STATS_MOTD_PLAIN)
    public String motdPlain() throws JsonProcessingException {
        final ObjectMapper objectMapper = ServerServicePlugin.getInjector().getInstance(ObjectMapper.class);

        return objectMapper.writeValueAsString(
                statService.getMotdPlain()
        );
    }

    @GET
    @Path("/minecraft")
    @Produces("application/json")
    @PermissionRequired(ResourcePermission.STATS_MOTD_MC)
    public String motdMinecraft() throws JsonProcessingException {
        final ObjectMapper objectMapper = ServerServicePlugin.getInjector().getInstance(ObjectMapper.class);

        return objectMapper.writeValueAsString(
                statService.getMotdWithMcColors()
        );
    }

}
