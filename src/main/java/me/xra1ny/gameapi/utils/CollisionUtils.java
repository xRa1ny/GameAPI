package me.xra1ny.gameapi.utils;

import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.models.gameobject.RCollideable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface CollisionUtils {
    static boolean collide(@NotNull RCollideable collideable, @NotNull RCollideable collideable2) {
        return collide(collideable.getX(), collideable.getY(), collideable.getWidth(), collideable.getHeight(), collideable2.getX(), collideable2.getY(), collideable2.getWidth(), collideable2.getHeight());
    }

    static boolean collide(@NotNull Point point, double width, double height, @NotNull Point point2, double width2, double height2) {
        return collide(point.getX(), point.getY(), width, height, point2.getX(), point2.getY(), width2, height2);
    }

    static boolean collide(double x, double y, double width, double height, double x2, double y2, double width2, double height2) {
        final double tolerance = RGame.getInstance().getConfigManager().getCollisionTolerance();

        final double ourLeft = x;
        final double ourRight = x + width+tolerance;
        final double ourTop = y;
        final double ourBottom = y + height+tolerance;

        final double theirLeft = x2 - tolerance;
        final double theirRight = x2 + width2 + tolerance;
        final double theirTop = y2 - tolerance;
        final double theirBottom = y2 + height2 + tolerance;

        return ourLeft < theirRight &&
                ourRight > theirLeft &&
                ourBottom > theirTop &&
                ourTop < theirBottom;
    }
}
