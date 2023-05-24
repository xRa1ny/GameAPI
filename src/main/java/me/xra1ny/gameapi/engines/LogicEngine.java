package me.xra1ny.gameapi.engines;

import lombok.Getter;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.engines.handlers.LogicHandler;
import me.xra1ny.gameapi.models.engine.REngine;
import org.jetbrains.annotations.NotNull;

public class LogicEngine extends REngine {
    @Getter(onMethod = @__(@NotNull))
    private LogicHandler logicHandler;

    @Override
    public void onEnable() {
        logicHandler = new LogicHandler(1000 / RGame.getInstance().getConfigManager().getTps(), this);
        logicHandler.enable();
    }
}
