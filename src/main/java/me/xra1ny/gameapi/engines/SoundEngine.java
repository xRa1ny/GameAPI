package me.xra1ny.gameapi.engines;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.RGame;
import me.xra1ny.gameapi.models.engine.REngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SoundEngine extends REngine {
    @Getter(onMethod = @__(@NotNull))
    private final File audioDirectory;

    @Getter(onMethod = @__(@NotNull))
    private final List<File> soundFiles = new ArrayList<>();

    public SoundEngine() {
        this.audioDirectory = new File("res/sounds");

        if (!this.audioDirectory.exists()) {
            if (!this.audioDirectory.mkdirs()) {
                throw new RuntimeException("Audio File Directory could not be created!");
            }
        }
    }

    private void loadSounds() {
        System.out.println("Loading Sounds into SoundEngine...");
        this.soundFiles.clear();
        final File[] files = audioDirectory.listFiles();

        if (files == null) {
            throw new RuntimeException("Could not load Sounds, no Sounds found in Directory " + audioDirectory.getName());
        }

        for (File file : files) {
            if (!file.getName().endsWith(".wav")) {
                continue;
            }

            System.out.println("Loading Sound " + file.getName() + "...");
            this.soundFiles.add(file);
            System.out.println("Sound " + file.getName() + " successfully loaded...");
        }
    }

    @Nullable
    public File getSoundFile(@NotNull String sound) {
        return this.soundFiles.stream()
                .filter(file -> file.getName().equals(sound))
                .findFirst().orElse(null);
    }

    public void playSound(@NotNull String sound) {
        playSound(sound, RGame.getInstance().getConfigManager().getVolume());
    }

    public void playSound(@NotNull String sound, float volume) {
        final File soundFile = getSoundFile(sound);
        if(soundFile == null) {
            return;
        }
        try {
            final Clip clip = AudioSystem.getClip();
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEnable() {
        loadSounds();
    }
}
