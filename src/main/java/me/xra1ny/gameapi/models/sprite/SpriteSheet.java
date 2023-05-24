package me.xra1ny.gameapi.models.sprite;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SpriteSheet {
    @Getter
    private final int gridWidth;

    @Getter(onMethod = @__(@NotNull))
    private final List<Sprite> sprites = new ArrayList<>();

    public SpriteSheet(@NotNull Sprite sprite, int gridWidth) {
        this.gridWidth = gridWidth;
        load(sprite);
    }

    private void load(@NotNull Sprite sprite) {
        if (sprite.getBufferedImage() != null) {
            final int amount = sprite.getBufferedImage().getWidth() / gridWidth;

            int currentX = 0;
            for (int i = 0; i < amount; i++) {
                sprites.add(new Sprite(sprite.getBufferedImage().getSubimage(currentX, 0, gridWidth, sprite.getBufferedImage().getHeight())));
                currentX += gridWidth;
            }
        } else {
            log.error("Could not Load Sprite to Spritesheet!");
        }
    }
}
