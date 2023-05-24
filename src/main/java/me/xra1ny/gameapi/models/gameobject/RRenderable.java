package me.xra1ny.gameapi.models.gameobject;

import me.xra1ny.gameapi.models.screen.RGameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface RRenderable extends RGameObject {
    boolean isAllowRender();
    void setAllowRender(boolean allowRender);

    void onRender(@NotNull RGameScreen screen, @NotNull Graphics2D gtd);
}
