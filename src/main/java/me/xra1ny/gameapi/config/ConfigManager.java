package me.xra1ny.gameapi.config;

import lombok.Getter;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.utils.FileUtils;
import me.xra1ny.gameapi.utils.PropertyUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Properties;

public class ConfigManager {
    @Getter(onMethod = @__(@NotNull))
    private final Properties config = new Properties();

    @Getter(onMethod = @__(@NotNull))
    private final File configFile = new File("res/config.properties");

    @Getter
    private final int fps, tps;

    @Getter
    private final double collisionTolerance;

    @Getter
    private final float volume;

    public ConfigManager(int tps, double collisionTolerance) {
        if(!this.configFile.exists()) {
            FileUtils.create(this.configFile);

            this.config.setProperty("fps", "144");
            this.config.setProperty("volume", "0.0");

            PropertyUtils.save(this.config, FileUtils.getOutputStream(this.configFile));

            RGame.getInstance().onPropertiesCreation();
        }

        PropertyUtils.load(this.config, FileUtils.getInputStream(this.configFile));

        this.fps = PropertyUtils.getInt(this.config, "fps", Integer.MIN_VALUE);
        this.tps = tps;
        this.collisionTolerance = collisionTolerance;
        this.volume = PropertyUtils.getFloat(this.config, "volume", Float.NaN);
    }
}
