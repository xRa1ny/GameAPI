package me.xra1ny.gameapi;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.applicationapi.RApplication;
import me.xra1ny.applicationapi.exceptions.ClassNotAnnotatedException;
import me.xra1ny.gameapi.annotations.GameInfo;
import me.xra1ny.gameapi.config.ConfigManager;
import me.xra1ny.gameapi.engines.EngineManager;
import me.xra1ny.gameapi.models.screen.RGameScreen;
import me.xra1ny.gameapi.screens.DefaultGameScreen;
import org.jetbrains.annotations.NotNull;

@Slf4j
public abstract class RGame extends RApplication {
    @Getter(onMethod = @__(@NotNull))
    private final ConfigManager configManager;

    @Getter(onMethod = @__(@NotNull))
    private final EngineManager engineManager;

    @Getter(onMethod = @__(@NotNull))
    private final DefaultGameScreen defaultGameScreen = new DefaultGameScreen();

    @Getter
    @Setter
    private int currentFps = 0;

    @Getter
    @Setter
    private int currentTps = 0;

    public RGame() {
        final GameInfo gameInfo = getClass().getDeclaredAnnotation(GameInfo.class);

        if(gameInfo == null) {
            throw new ClassNotAnnotatedException(getClass(), GameInfo.class);
        }

        show(this.defaultGameScreen);

        this.configManager = new ConfigManager(gameInfo.tps(), gameInfo.collisionTolerance());
        this.engineManager = new EngineManager();

        show(defaultGameScreen);
        onEnable();
    }

    /**
     * called when a new properties file gets created for this game
     */
    public abstract void onPropertiesCreation();

    /**
     * called when this game enables
     */
    public abstract void onEnable();

    @NotNull
    @Override
    public RGameScreen getCurrentScreen() {
        return (RGameScreen) super.getCurrentScreen();
    }

    @NotNull
    public static RGame getInstance() {
        return (RGame) RApplication.getInstance();
    }
}
