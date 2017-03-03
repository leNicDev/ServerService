package de.lenic.serverservice.spigot.services.stats;

import de.lenic.serverservice.spigot.server.resources.stats.motd.MotdResponse;
import de.lenic.serverservice.spigot.server.resources.stats.playercount.CurrentPlayerCountResponse;
import de.lenic.serverservice.spigot.server.resources.stats.playercount.MaxPlayerCountResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class StatServiceImpl implements IStatService {

    @Override
    public CurrentPlayerCountResponse getCurrentPlayerCount() {
        CurrentPlayerCountResponse response = new CurrentPlayerCountResponse(
                Bukkit.getOnlinePlayers().size()
        );
        return response;
    }

    @Override
    public MaxPlayerCountResponse getMaxPlayerCount() {
        MaxPlayerCountResponse response = new MaxPlayerCountResponse(
                Bukkit.getMaxPlayers()
        );
        return response;
    }


    @Override
    public MotdResponse getMotdPlain() {
        MotdResponse response = new MotdResponse(
                ChatColor.stripColor(Bukkit.getMotd())
        );
        return response;
    }

    @Override
    public MotdResponse getMotdWithMcColors() {
        MotdResponse response = new MotdResponse(
                Bukkit.getMotd()
        );
        return response;
    }

}
