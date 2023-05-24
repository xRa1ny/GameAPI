package me.xra1ny.gameapi.models.engine;

import lombok.Getter;
import me.xra1ny.gameapi.models.task.RRepeatableTask;
import org.jetbrains.annotations.NotNull;

public abstract class REngineHandler extends RRepeatableTask {
    @Getter(onMethod = @__(@NotNull))
    private final REngine engine;

    protected REngineHandler(@NotNull REngine engine) {
        this.engine = engine;
    }

    protected REngineHandler(int interval, @NotNull REngine engine) {
        super(interval);

        this.engine = engine;
    }

    public abstract void onEnable();
}
