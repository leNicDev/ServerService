package de.lenic.serverservice.spigot.server.handler;

import spark.Route;

public interface ServerServiceRoute extends Route {

    String getRequiredPermission();

}
