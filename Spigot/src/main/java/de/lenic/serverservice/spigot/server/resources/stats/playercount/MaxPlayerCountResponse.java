package de.lenic.serverservice.spigot.server.resources.stats.playercount;

/**
 * Response to a maximum player count request
 * @see PlayerCountResource
 */
public class MaxPlayerCountResponse {

    private int maxPlayerCount;


    public MaxPlayerCountResponse(int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }


    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public void setMaxPlayerCount(int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }

}
