package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.models.engine.REngine;
import me.xra1ny.gameapi.models.engine.REngineHandler;
import me.xra1ny.gameapi.models.gameobject.RGameObject;
import me.xra1ny.gameapi.models.gameobject.RTickable;
import me.xra1ny.gameapi.models.screen.RGameScreen;
import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;

public class LogicHandler extends REngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private TPSHandler tpsHandler;
    @Getter
    @Setter
    private int countedTicks;

    public LogicHandler(int interval, @NotNull REngine engine) {
        super(interval, engine);
    }

    @Override
    public void onEnable() {
        tpsHandler = new TPSHandler(getEngine(), this);
        tpsHandler.enable();
    }

    @Override
    public void tick() {
        countedTicks++;
        final RGameScreen screen = RGame.getInstance().getCurrentScreen();
        try {
            for(RGameObject object : screen.getScreenManager().getObjects()) {
                if(object instanceof RTickable) {
                    final RTickable tickable = (RTickable) object;

                    if(!tickable.isAllowTick()) {
                        continue;
                    }

                    tickable.handleTick(screen);
                }
            }
        }catch(ConcurrentModificationException ignored) {}
    }
}
