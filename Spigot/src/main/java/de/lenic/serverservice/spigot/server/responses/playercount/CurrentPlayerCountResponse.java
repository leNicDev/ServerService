package de.lenic.serverservice.spigot.server.responses.playercount;

import de.lenic.serverservice.spigot.server.resources.stats.playercount.PlayerCountResource;

/**
 * Response to a current player count request
 * @see PlayerCountResource
 */
public class CurrentPlayerCountResponse {

    private int currentPlayerCount;


    public CurrentPlayerCountResponse(int currentPlayerCount) {
        this.currentPlayerCount = currentPlayerCount;
    }


    public int getCurrentPlayerCount() {
        return currentPlayerCount;
    }

    public void setCurrentPlayerCount(int currentPlayerCount) {
        this.currentPlayerCount = currentPlayerCount;
    }

}
