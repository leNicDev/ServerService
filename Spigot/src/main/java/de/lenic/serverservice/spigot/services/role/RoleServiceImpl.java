package de.lenic.serverservice.spigot.services.role;

import de.lenic.serverservice.spigot.auth.Role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RoleServiceImpl implements IRoleService {

    private Map<String, Role> roles;


    public RoleServiceImpl() {
        roles = new HashMap<>();
    }


    @Override
    public Role getRole(String identifier) {
        return roles.get(identifier);
    }

    @Override
    public Role getRoleByToken(String token) {
        for (Role role : roles.values()) {
            if (role.getToken().equals(token)) {
                return role;
            }
        }

        return null;
    }

    @Override
    public Collection<Role> getRoles() {
        return roles.values();
    }

    @Override
    public void addRole(Role role) {
        roles.put(role.getIdentifier(), role);
    }

}
