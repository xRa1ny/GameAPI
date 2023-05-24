package me.xra1ny.gameapi.models.gameobject;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.applicationapi.exceptions.ClassNotAnnotatedException;
import me.xra1ny.gameapi.annotations.EntityInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class REntity implements RRenderable, RTickable, RCollideable {
    @Getter(onMethod = @__(@NotNull))
    private final UUID uniqueId;

    @Getter
    @Setter
    private double x, y, width, height, xVelocity, yVelocity, maxXVelocity, maxYVelocity;

    @Getter
    @Setter
    private boolean allowTick = true, allowRender = true;

    @Getter
    @Setter
    private int skipFrames, skipTicks;

    @Getter(onMethod = @__(@NotNull))
    private final List<Class<? extends RCollideable>> collideables = new ArrayList<>();

    public REntity() {
        final EntityInfo info = getClass().getDeclaredAnnotation(EntityInfo.class);

        if(info == null) {
            throw new ClassNotAnnotatedException(getClass(), EntityInfo.class);
        }

        this.uniqueId = UUID.randomUUID();
        this.width = info.width();
        this.height = info.height();
        this.maxXVelocity = info.maxXVelocity();
        this.maxYVelocity = info.maxYVelocity();
        this.collideables.addAll(Arrays.asList(info.collideableGameObjects()));
    }

    @Override
    public void addCollideable(@NotNull Class<? extends RCollideable> collideable) {
        if(this.collideables.contains(collideable)) {
            return;
        }

        this.collideables.add(collideable);
    }

    @Override
    public void removeCollideable(@NotNull Class<? extends RCollideable> collideable) {
        if(!this.collideables.contains(collideable)) {
            return;
        }

        this.collideables.remove(collideable);
    }
}
