package de.lenic.serverservice.spigot.server.responses.cmd;

/**
 * Response to a command request
 * @see de.lenic.serverservice.spigot.server.resources.cmd.CommandResource
 */
public class CommandResponse {

    private boolean success;


    public CommandResponse(boolean success) {
        this.success = success;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
