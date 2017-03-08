package de.lenic.serverservice.spigot.server.resources.cmd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.lenic.serverservice.spigot.ServerServicePlugin;
import de.lenic.serverservice.spigot.config.annotations.PermissionRequired;
import de.lenic.serverservice.spigot.server.requests.CommandRequest;
import de.lenic.serverservice.spigot.server.resources.ResourcePermission;
import de.lenic.serverservice.spigot.services.cmd.ICommandService;
import io.undertow.util.StatusCodes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.io.IOException;

@Path("/cmd/execute")
public class CommandResource {

    private ICommandService commandService;


    public CommandResource() {
        commandService = ServerServicePlugin.getInjector().getInstance(ICommandService.class);
    }


    @POST
    @Path("/console")
    @Produces("application/json")
    @PermissionRequired(ResourcePermission.CMD_EXECUTE_CONSOLE)
    public void executeConsoleCommand(@Suspended AsyncResponse response, String json) throws IOException {
        final ObjectMapper objectMapper = ServerServicePlugin.getInjector().getInstance(ObjectMapper.class);

        final CommandRequest request = objectMapper.readValue(json, CommandRequest.class);

        commandService.executeConsoleCommand(request.getCommand()).receive((commandResponse) -> {
            try {
                response.resume(
                        objectMapper.writeValueAsString(commandResponse)
                );
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
                response.cancel(StatusCodes.INTERNAL_SERVER_ERROR);
            }
        });
    }

}
