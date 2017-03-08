package de.lenic.serverservice.spigot.services.cmd;

import de.lenic.serverservice.spigot.ServerServicePlugin;
import de.lenic.serverservice.spigot.concurrent.Promise;
import de.lenic.serverservice.spigot.server.responses.cmd.CommandResponse;
import org.bukkit.Bukkit;

public class CommandServiceImpl implements ICommandService {

    @Override
    public Promise<CommandResponse> executeConsoleCommand(String command) {
        Promise<CommandResponse> promise = new Promise<>();

        Bukkit.getScheduler().runTask(ServerServicePlugin.getInstance(), () -> {
            final CommandResponse response = new CommandResponse(
                    Bukkit.dispatchCommand(
                            Bukkit.getConsoleSender(),
                            command
                    )
            );

           promise.supply(response);
        });

        return promise;
    }

}
