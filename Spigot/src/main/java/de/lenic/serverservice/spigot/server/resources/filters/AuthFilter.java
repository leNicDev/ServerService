package de.lenic.serverservice.spigot.server.resources.filters;

import de.lenic.serverservice.spigot.ServerServicePlugin;
import de.lenic.serverservice.spigot.auth.Role;
import de.lenic.serverservice.spigot.config.annotations.PermissionRequired;
import de.lenic.serverservice.spigot.services.role.IRoleService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    // Pre defined responses
    private static final Response UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).build();

    @Context
    private ResourceInfo resourceInfo;

    private IRoleService roleService;


    public AuthFilter() {
        roleService = ServerServicePlugin.getInjector().getInstance(IRoleService.class);
    }


    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        final String token = context.getHeaderString("Authorization");

        // No token provided
        if(token == null) {
            context.abortWith(UNAUTHORIZED);
            return;
        }

        final Role role = roleService.getRoleByToken(token);

        // Invalid token
        if(role == null) {
            context.abortWith(UNAUTHORIZED);
            return;
        }

        final Method targetMethod = resourceInfo.getResourceMethod();

        if(targetMethod.isAnnotationPresent(PermissionRequired.class)) {
            PermissionRequired permissionAnnotation = targetMethod.getAnnotation(PermissionRequired.class);

            // Role has no permission for accessing that resource
            if(!role.hasPermission(permissionAnnotation.value())) {
                context.abortWith(UNAUTHORIZED);
                return;
            }
        }
    }

}
