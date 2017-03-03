package de.lenic.serverservice.spigot.services.stats;

import de.lenic.serverservice.spigot.server.resources.stats.motd.MotdResponse;
import de.lenic.serverservice.spigot.server.resources.stats.playercount.CurrentPlayerCountResponse;
import de.lenic.serverservice.spigot.server.resources.stats.playercount.MaxPlayerCountResponse;

public interface IStatService {

    CurrentPlayerCountResponse getCurrentPlayerCount();
    MaxPlayerCountResponse getMaxPlayerCount();

    MotdResponse getMotdPlain();
    MotdResponse getMotdWithMcColors();

}
