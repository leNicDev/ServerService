package de.lenic.serverservice.spigot.server.resources.stats.playercount;

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
