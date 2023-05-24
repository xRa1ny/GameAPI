package me.xra1ny.gameapi.engines;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class EngineManager {
    @Getter(onMethod = @__(@NotNull))
    private final RenderingEngine renderingEngine = new RenderingEngine();

    @Getter(onMethod = @__(@NotNull))
    private final LogicEngine logicEngine = new LogicEngine();

    @Getter(onMethod = @__(@NotNull))
    private final SoundEngine soundEngine = new SoundEngine();

    public EngineManager() {
        this.logicEngine.enable();
        this.renderingEngine.enable();
        this.soundEngine.enable();
    }
}
