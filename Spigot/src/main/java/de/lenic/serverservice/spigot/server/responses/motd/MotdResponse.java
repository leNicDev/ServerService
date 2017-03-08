package de.lenic.serverservice.spigot.server.responses.motd;

import de.lenic.serverservice.spigot.server.resources.stats.motd.MotdResource;

/**
 * Response to a motd request
 * @see MotdResource
 */
public class MotdResponse {

    private String motd;


    public MotdResponse(String motd) {
        this.motd = motd;
    }


    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

}
