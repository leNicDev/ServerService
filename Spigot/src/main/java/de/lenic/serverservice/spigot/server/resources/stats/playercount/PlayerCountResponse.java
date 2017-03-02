/*
 * (C) Copyright 2017 DCR Network (https://dcr.bz/).
 * 
 * All rights reserved to the copyright holder.
 */
package de.lenic.serverservice.spigot.server.resources.stats.playercount;

/**
 * Response to a player count request
 * @see PlayerCountResource
 */
public class PlayerCountResponse {

    private int playerCount;


    public PlayerCountResponse(int playerCount) {
        this.playerCount = playerCount;
    }


    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

}
