package de.lenic.serverservice.spigot.auth;

import java.util.List;

/**
 * Class that represents an access level for resources.
 */
public class Role {

    private String identifier;
    private String token;
    private List<String> permissions;


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(String permission) {
        return hasWildcardPermission() || permissions.contains(permission);
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }


    public boolean hasWildcardPermission() {
        return permissions.contains("*");
    }

}
