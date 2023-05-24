package me.xra1ny.gameapi.models.gameobject;

import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.utils.CollisionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public interface RCollideable extends RGameObject {
    // TODO: fix mtv sat collision tolerance not working properly...
    //

    double getXVelocity();
    void setXVelocity(double xVelocity);

    double getYVelocity();
    void setYVelocity(double yVelocity);

    List<Class<? extends RCollideable>> getCollideables();
    void addCollideable(@NotNull Class<? extends RCollideable> collideable);
    void removeCollideable(@NotNull Class<? extends RCollideable> collideable);

    default boolean collidesOnX(double x, double width) {
        final double tolerance = RGame.getInstance().getConfigManager().getCollisionTolerance();

        final double ourLeft = getX() - tolerance;
        final double ourRight = getX() + getWidth() + tolerance;

        final double theirLeft = x - tolerance;
        final double theirRight = x + width + tolerance;

        return ourLeft < theirRight &&
                ourRight > theirLeft;
    }

    default boolean collidesOnY(double y, double height) {
        final double tolerance = RGame.getInstance().getConfigManager().getCollisionTolerance();

        final double ourTop = getY() - tolerance;
        final double ourBottom = getY() + getHeight() + tolerance;

        final double theirTop = y - tolerance;
        final double theirBottom = y + height + tolerance;

        return ourTop < theirBottom &&
                ourBottom > theirTop;
    }

    default boolean collidesWith(@NotNull RCollideable collideable) {
        return collidesWith(collideable.getX(), collideable.getY(), collideable.getWidth(), collideable.getHeight());
    }

    default boolean collidesWith(@NotNull Rectangle rectangle) {
        return collidesWith(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    default boolean collidesWith(@NotNull Point point) {
        return collidesWith(point.getX(), point.getY(), 1, 1);
    }

    default boolean collidesWith(double x, double y, double width, double height) {
        return CollisionUtils.collide(getX(), getY(), getWidth(), getHeight(), x, y, width, height);
    }

    default void handleCollision(@NotNull RCollideable collideable) {
        if(!getCollideables().contains(collideable.getClass())) {
            return;
        }

        if(!collidesWith(collideable)) {
            return;
        }

        final CollisionSide ourCollisionSide = getCollisionSide(collideable);
        final CollisionSide theirCollisionSide = collideable.getCollisionSide(this);

        if(getYVelocity() != 0D) {
            while(collidesWith(collideable)) {
                setY(getY()-(Math.signum(getYVelocity())*.1));
            }

            setYVelocity(0D);
        }

        if(getXVelocity() != 0D) {
            while(collidesWith(collideable)) {
                setX(getX()-(Math.signum(getXVelocity())*.1));
            }

            setXVelocity(0D);
        }

        collideable.onCollision(this, theirCollisionSide);
        onCollision(collideable, ourCollisionSide);
    }

    @Nullable
    default CollisionSide getCollisionSide(@NotNull RCollideable collideable) {
        return getCollisionSide(collideable.getX(), collideable.getY(), collideable.getWidth(), collideable.getHeight());
    }

    @Nullable
    default CollisionSide getCollisionSide(@NotNull Rectangle rectangle) {
        return getCollisionSide(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Nullable
    default CollisionSide getCollisionSide(@NotNull Point point, double width, double height) {
        return getCollisionSide(point.x, point.y, width, height);
    }

    @NotNull
    default CollisionSide getCollisionSide(double x, double y, double width, double height) {
        if(!collidesWith(x, y, width, height)) {
            return CollisionSide.NONE;
        }

        final double tolerance = RGame.getInstance().getConfigManager().getCollisionTolerance();

        double mtvX;
        double mtvY;

        if(getX()+ getWidth()+tolerance > x+width+tolerance) {
            mtvX = (x+width+tolerance) - getX();
        }else {
            mtvX = x - (getX()+ getWidth()+tolerance);
        }

        if(getY()+ getHeight()+tolerance > y+height+tolerance) {
            mtvY = (y+height+tolerance) - getY();
        }else {
            mtvY = y - (getY()+ getHeight()+tolerance);
        }

        if(Math.abs(mtvX) < Math.abs(mtvY)) {
            if(mtvX < 0) {
                return CollisionSide.RIGHT;
            }else {
                return CollisionSide.LEFT;
            }
        }else {
            if(mtvY < 0) {
                return CollisionSide.BOTTOM;
            }else {
                return CollisionSide.TOP;
            }
        }
    }

    /**
     * called when this collideable collides with another
     * @param collideable the collideable this collideable collides with
     */
    void onCollision(@NotNull RCollideable collideable, @NotNull CollisionSide collisionSide);
}
