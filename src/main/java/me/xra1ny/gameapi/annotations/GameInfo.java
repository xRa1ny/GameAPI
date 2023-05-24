package me.xra1ny.gameapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GameInfo {
    /**
     * defines the rate in which logic ticks are performed each second
     */
    int tps();

    /**
     * defines the tolerance in which collisions are registered
     */
    double collisionTolerance();
}
