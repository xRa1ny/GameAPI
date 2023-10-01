package me.xra1ny.gameapi.models.screen;

import lombok.Getter;
import me.xra1ny.gameapi.exceptions.GameObjectAlreadyRegisteredException;
import me.xra1ny.gameapi.exceptions.GameObjectNotRegisteredException;
import me.xra1ny.gameapi.models.gameobject.RGameObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameScreenManager {
    @Getter(onMethod = @__(@NotNull))
    private final RGameScreen screen;

    @Getter(onMethod = @__({@NotNull, @Unmodifiable}))
    private final List<RGameObject> objects = new ArrayList<>();

    public GameScreenManager(@NotNull RGameScreen screen) {
        this.screen = screen;
    }

    public boolean isRegistered(@NotNull RGameObject object) {
        return this.objects.contains(object);
    }

    public void register(@NotNull RGameObject object) throws GameObjectAlreadyRegisteredException {
        if(isRegistered(object)) {
            throw new GameObjectAlreadyRegisteredException(object, this.screen);
        }

        this.objects.add(object);
        object.onRegister(screen);
    }

    public void unregister(@NotNull RGameObject object) throws GameObjectNotRegisteredException {
        if(!isRegistered(object)) {
            throw new GameObjectNotRegisteredException(object, this.screen);
        }

        this.objects.remove(object);
        object.onUnregister(screen);
    }

    @Nullable
    public <T extends RGameObject> T get(@NotNull UUID uuid) {
        return (T) this.objects.stream()
                .filter(object -> object.getUniqueId().equals(uuid))
                .findFirst().orElse(null);
    }
}
