package me.xra1ny.gameapi.models.sprite;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class Sprite {
    @Getter(onMethod = @__(@Nullable))
    private BufferedImage bufferedImage;

    public Sprite(@NotNull String filename) {
        load(filename);
    }

    public Sprite(@NotNull BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;

    }

    private void load(@NotNull String filename) {
        try {
            final File file = new File("res/textures/" + filename);
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            log.error("Could not Load Sprite from File " + filename);
        }
    }
}
