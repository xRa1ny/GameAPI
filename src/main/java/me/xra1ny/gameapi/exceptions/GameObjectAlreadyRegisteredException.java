package me.xra1ny.gameapi.exceptions;

import me.xra1ny.gameapi.models.exception.RGameException;
import me.xra1ny.gameapi.models.gameobject.RGameObject;
import me.xra1ny.gameapi.models.screen.RGameScreen;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GameObjectAlreadyRegisteredException extends RGameException {
    public GameObjectAlreadyRegisteredException(@NotNull RGameObject object, @NotNull RGameScreen screen) {
        super("game object " + object + " is already registered on screen " + screen + "!");
    }

    public GameObjectAlreadyRegisteredException(@NotNull UUID uniqueId, @NotNull RGameScreen screen) {
        super("game object with uuid " + uniqueId + " is already registered on screen " + screen + "!");
    }
}
