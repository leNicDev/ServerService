package de.lenic.serverservice.spigot.inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import de.lenic.serverservice.spigot.services.role.IRoleService;
import de.lenic.serverservice.spigot.services.role.RoleServiceImpl;
import de.lenic.serverservice.spigot.services.stats.IStatService;
import de.lenic.serverservice.spigot.services.stats.StatServiceImpl;

/**
 * All DI bindings are registered here.
 * The binding methods are provided by the {@link com.google.inject.AbstractModule} class.
 */
public class ServerServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bindServices();
        bindOthers();
    }


    /**
     * Service bindings are registered in this method.
     */
    private void bindServices() {
        bind(IRoleService.class).toInstance(new RoleServiceImpl());

        bind(IStatService.class).toInstance(new StatServiceImpl());
    }

    /**
     * Non-service bindings are registered in this method.
     */
    private void bindOthers() {
        bind(ObjectMapper.class).toInstance(new ObjectMapper());
    }

}
