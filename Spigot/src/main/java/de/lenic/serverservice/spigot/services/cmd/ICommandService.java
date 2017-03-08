package de.lenic.serverservice.spigot.services.cmd;

import de.lenic.serverservice.spigot.concurrent.Promise;
import de.lenic.serverservice.spigot.server.responses.cmd.CommandResponse;

public interface ICommandService {

    Promise<CommandResponse> executeConsoleCommand(String command);

}
