package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.annotations.RepeatableTaskInfo;
import me.xra1ny.gameapi.models.engine.REngine;
import me.xra1ny.gameapi.models.engine.REngineHandler;
import org.jetbrains.annotations.NotNull;

@RepeatableTaskInfo(interval = 1000)
public class TPSHandler extends REngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private final LogicHandler logicHandler;

    public TPSHandler(@NotNull REngine engine, @NotNull LogicHandler logicHandler) {
        super(engine);

        this.logicHandler = logicHandler;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void tick() {
        if(logicHandler != null) {
            RGame.getInstance().setCurrentTps(logicHandler.getCountedTicks());
            logicHandler.setCountedTicks(0);
        }
    }
}
