package me.xra1ny.gameapi.models.gameobject;

import me.xra1ny.gameapi.models.screen.RGameScreen;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public interface RTickable extends RGameObject {
    double getXVelocity();
    void setXVelocity(double xVelocity);

    double getYVelocity();
    void setYVelocity(double yVelocity);

    boolean isAllowTick();
    void setAllowTick(boolean allowTick);

    default void handleTick(@NotNull RGameScreen screen) {
        onTick(screen);

        setX(getX()+getXVelocity());
        setY(getY()+getYVelocity());

        if(this instanceof RCollideable) {
            for(RCollideable collideable : screen.getScreenManager().getObjects().stream()
                    .filter(object -> !object.equals(this))
                    .filter(RCollideable.class::isInstance)
                    .map(RCollideable.class::cast)
                    .filter(((RCollideable) this)::collidesWith)
                    .collect(Collectors.toList())) {
                ((RCollideable) this).handleCollision(collideable);
            }
        }
    }

    void onTick(@NotNull RGameScreen screen);
}