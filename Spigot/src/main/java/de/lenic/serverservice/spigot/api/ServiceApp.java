package de.lenic.serverservice.spigot.api;

import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public abstract class ServiceApp extends Application {

    private Set<Class<?>> classes;


    public ServiceApp() {
        this.classes = new HashSet<>();

        classes.add(ResteasyJackson2Provider.class);
    }


    protected void addClass(Class<?> clazz) {
        classes.add(clazz);
    }

    protected boolean removeClass(Class<?> clazz) {
        return classes.remove(clazz);
    }


    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
