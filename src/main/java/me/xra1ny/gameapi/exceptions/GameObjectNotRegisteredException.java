package me.xra1ny.gameapi.exceptions;

import me.xra1ny.gameapi.models.exception.RGameException;
import me.xra1ny.gameapi.models.gameobject.RGameObject;
import me.xra1ny.gameapi.models.screen.RGameScreen;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GameObjectNotRegisteredException extends RGameException {
    public GameObjectNotRegisteredException(@NotNull RGameObject object, @NotNull RGameScreen screen) {
        super("game object " + object + " is not yet registered on screen " + screen + "!");
    }

    public GameObjectNotRegisteredException(@NotNull UUID uniqueId, @NotNull RGameScreen screen) {
        super("game object with uuid " + uniqueId + " is not yet registered on screen " + screen + "!");
    }
}
