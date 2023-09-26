package com.kevinpina.configs;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.kevinpina.interceptors.Logging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

/**
 * With this Annotation @Servicio we can apply logs to any methods of classes
 * annotated with this annotation.
 */

@Logging
@ApplicationScoped
@Stereotype
@Named
@Retention(RUNTIME)
@Target(TYPE)
public @interface Servicio {

}
