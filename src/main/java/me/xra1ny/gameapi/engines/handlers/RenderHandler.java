package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.engines.RenderingEngine;
import me.xra1ny.gameapi.models.engine.REngineHandler;
import me.xra1ny.gameapi.models.gameobject.RGameObject;
import me.xra1ny.gameapi.models.gameobject.RRenderable;
import me.xra1ny.gameapi.models.screen.RGameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ConcurrentModificationException;

@Slf4j
public class RenderHandler extends REngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private FPSHandler fpsHandler;

    @Getter
    @Setter
    private int countedFrames = 0;

    public RenderHandler(int interval, @NotNull RenderingEngine renderingEngine) {
        super(interval, renderingEngine);
    }


    @Override
    public void onEnable() {
        fpsHandler = new FPSHandler(getEngine(), this);
        fpsHandler.enable();
    }

    @Override
    public void tick() {
        countedFrames++;
        final RGameScreen screen = RGame.getInstance().getCurrentScreen();
        screen.repaint();
    }

    @NotNull
    @Override
    public RenderingEngine getEngine() {
        return (RenderingEngine) super.getEngine();
    }
}
