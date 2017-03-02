/*
 * (C) Copyright 2017 DCR Network (https://dcr.bz/).
 * 
 * All rights reserved to the copyright holder.
 */
package de.lenic.serverservice.spigot.server.resources.stats.playercount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/stats/playercount")
public class PlayerCountResource {

    @GET
    @Produces("application/json")
    public String playerCount() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(new PlayerCountResponse(Bukkit.getOnlinePlayers().size()));
    }

}
