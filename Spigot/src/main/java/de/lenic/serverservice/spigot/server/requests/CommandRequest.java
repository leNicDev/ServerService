package de.lenic.serverservice.spigot.server.requests;

public class CommandRequest {

    private String command;


    public CommandRequest() {
    }

    public CommandRequest(String command) {
        this.command = command;
    }


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
