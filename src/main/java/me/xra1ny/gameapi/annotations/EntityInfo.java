package me.xra1ny.gameapi.annotations;

import me.xra1ny.gameapi.models.gameobject.RCollideable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityInfo {
    /**
     * Defines the width of this entity instance
     */
    int width();

    /**
     * Defines the height of this entity instance
     */
    int height();

    /**
     *
     */
    double maxXVelocity();

    /**
     *
     */
    double maxYVelocity();

    Class<? extends RCollideable>[] collideableGameObjects() default {};
}
