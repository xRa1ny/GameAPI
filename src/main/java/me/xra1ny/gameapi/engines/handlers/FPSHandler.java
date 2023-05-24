package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.annotations.RepeatableTaskInfo;
import me.xra1ny.gameapi.models.engine.REngine;
import me.xra1ny.gameapi.models.engine.REngineHandler;
import org.jetbrains.annotations.NotNull;

@Slf4j
@RepeatableTaskInfo(interval = 1000)
public class FPSHandler extends REngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private final RenderHandler renderHandler;

    public FPSHandler(@NotNull REngine engine, @NotNull RenderHandler renderHandler) {
        super(engine);

        this.renderHandler = renderHandler;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void tick() {
        if(renderHandler != null) {
            RGame.getInstance().setCurrentFps(renderHandler.getCountedFrames());
            renderHandler.setCountedFrames(0);
        }
    }
}
