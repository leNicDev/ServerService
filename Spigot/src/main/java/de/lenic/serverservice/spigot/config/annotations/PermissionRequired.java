package de.lenic.serverservice.spigot.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that sets the required permission to access a resource.
 * Permissions are provided by a {@link de.lenic.serverservice.spigot.auth.Role}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionRequired {

    String value();

}
