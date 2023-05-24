package me.xra1ny.gameapi.models.gameobject;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

public interface RGameObject extends Serializable {
    UUID getUniqueId();

    double getX();
    void setX(double x);

    double getY();
    void setY(double y);

    double getWidth();
    void setWidth(double width);

    double getHeight();
    void setHeight(double height);

    default double getAngle(double x, double y) {
        return Math.atan2(y-getY(), x-getX());
    }

    default double getAngle(@NotNull Point point) {
        return getAngle(point.getX(), point.getY());
    }

    default double getAngle(@NotNull RGameObject object) {
        return getAngle(object.getX(), object.getY());
    }

    default double getDistance(double x, double y) {
        return Point.distance(getX(), getY(), x, y);
    }

    default double getDistance(@NotNull RGameObject object) {
        return getDistance(object.getX(), object.getY());
    }

    default double getDistance(@NotNull Point point) {
        return getDistance(point.getX(), point.getY());
    }
}
