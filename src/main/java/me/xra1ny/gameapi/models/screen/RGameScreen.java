package me.xra1ny.gameapi.models.screen;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.applicationapi.models.screen.RScreen;
import me.xra1ny.gameapi.models.gameobject.RGameObject;
import me.xra1ny.gameapi.models.gameobject.RRenderable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ConcurrentModificationException;

@Slf4j
public abstract class RGameScreen extends RScreen {
    @Getter(onMethod = @__(@NotNull))
    private final GameScreenManager screenManager;

    public RGameScreen() {
        this.screenManager = new GameScreenManager(this);
    }

    /**
     * called when the default rendering engine requests a repaint on this game screen
     */
    public abstract void onRender(@NotNull Graphics2D gtd);
    public abstract void afterRender(@NotNull Graphics2D gtd);

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        final Graphics2D gtd = (Graphics2D) g;

        onRender(gtd);
        try {
            for(RGameObject object : this.screenManager.getObjects()) {
                if(!(object instanceof RRenderable)) {
                    continue;
                }

                final RRenderable renderable = (RRenderable) object;

                if(!renderable.isAllowRender()) {
                    continue;
                }

                renderable.onRender(this, gtd);
            }
        }catch(ConcurrentModificationException ignored) {}
        afterRender(gtd);
    }
}
