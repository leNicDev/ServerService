package de.lenic.serverservice.spigot;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerServiceApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return getResourceClasses();
    }

    private Set<Class<?>> getResourceClasses() {
        // Find names of all classes in resource package
        final List<String> resourceClassNames = new FastClasspathScanner("de.lenic.serverservice.spigot.server.resources")
                .scan()
                .getNamesOfAllStandardClasses();

        // Find all resource classes
        Set<Class<?>> resourceClasses = new HashSet<>();

        resourceClasses.add(ResteasyJackson2Provider.class);

        resourceClassNames.forEach(className -> {
            try {
                Class<?> clazz = Class.forName(className);
                resourceClasses.add(clazz);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        return resourceClasses;
    }

}
