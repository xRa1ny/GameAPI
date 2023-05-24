package me.xra1ny.gameapi.models.engine;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class REngine {
    @Getter(onMethod = @__(@NotNull))
    private final List<REngineHandler> handlers = new ArrayList<>();

    protected REngine() {
    }

    /**
     * enables this engine
     */
    public void enable() {
        onEnable();
    }

    /**
     * called when this engine enables
     */
    public abstract void onEnable();
}
