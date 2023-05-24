package me.xra1ny.gameapi.engines;

import lombok.Getter;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.engines.handlers.RenderHandler;
import me.xra1ny.gameapi.models.engine.REngine;
import org.jetbrains.annotations.NotNull;

public class RenderingEngine extends REngine {
    @Getter(onMethod = @__(@NotNull))
    private RenderHandler renderHandler;

    @Override
    public void onEnable() {
        renderHandler = new RenderHandler(1000 / RGame.getInstance().getConfigManager().getFps(), this);
        renderHandler.enable();
    }
}
