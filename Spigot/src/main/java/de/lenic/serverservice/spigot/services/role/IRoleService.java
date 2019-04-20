package de.lenic.serverservice.spigot.services.role;

import de.lenic.serverservice.spigot.auth.Role;

import java.util.Collection;

public interface IRoleService {

    Role getRole(String identifier);

    Role getRoleByToken(String token);

    Collection<Role> getRoles();

    void addRole(Role role);

}
