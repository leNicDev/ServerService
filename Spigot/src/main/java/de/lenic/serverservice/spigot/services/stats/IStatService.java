package de.lenic.serverservice.spigot.services.stats;

import de.lenic.serverservice.spigot.server.responses.motd.MotdResponse;
import de.lenic.serverservice.spigot.server.responses.playercount.CurrentPlayerCountResponse;
import de.lenic.serverservice.spigot.server.responses.playercount.MaxPlayerCountResponse;

public interface IStatService {

    CurrentPlayerCountResponse getCurrentPlayerCount();
    MaxPlayerCountResponse getMaxPlayerCount();

    MotdResponse getMotdPlain();
    MotdResponse getMotdWithMcColors();

}
