package de.lenic.serverservice.spigot.server.resources.filters;

import de.lenic.serverservice.spigot.ServerServicePlugin;
import de.lenic.serverservice.spigot.auth.Role;
import de.lenic.serverservice.spigot.server.handler.ServerServiceRoute;
import de.lenic.serverservice.spigot.services.role.IRoleService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class AuthFilter implements Filter {

    private IRoleService roleService;


    public AuthFilter() {
        roleService = ServerServicePlugin.getInstance().getRoleService();
    }


    @Override
    public void handle(Request request, Response response) {
        final String token = request.headers("Authorization");

        // No token provided
        if (token == null) {
            halt(HttpStatus.UNAUTHORIZED_401);
            return;
        }

        final Role role = roleService.getRoleByToken(token);

        // Invalid token
        if (role == null) {
            halt(HttpStatus.UNAUTHORIZED_401);
            return;
        }

        // Get route
        final ServerServiceRoute route = ServerServicePlugin.getInstance().getServerManager()
                .getRoute(request.pathInfo());

        // Route is not registered
        if (route == null) {
            halt(HttpStatus.NOT_FOUND_404);
            return;
        }

        // Route does not require authentication
        if (route.getRequiredPermission() == null || route.getRequiredPermission().isEmpty()) {
            return;
        }

        // Role has no permission for accessing that resource
        if (!role.hasPermission(route.getRequiredPermission())) {
            halt(HttpStatus.UNAUTHORIZED_401);
            return;
        }
    }

}
