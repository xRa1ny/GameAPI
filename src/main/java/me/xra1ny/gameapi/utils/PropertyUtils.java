package me.xra1ny.gameapi.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public interface PropertyUtils {
    /**
     * Loads the Contents of the specified InputStream to the specified Properties
     */
    static void load(@NotNull Properties properties, @NotNull InputStream inputStream) {
        try {
            properties.load(inputStream);
        } catch (IOException ignored) {}
    }

    /**
     * Saves the specified Properties to the specified OutputStream
     */
     static void save(@NotNull Properties properties, @NotNull OutputStream outputStream) {
        try {
            properties.store(outputStream, null);
        } catch (IOException ignored) {}
    }

     static String getString(@NotNull Properties properties, @NotNull String key, @NotNull String def) {
        return String.valueOf(getObject(properties, key, def));
    }

    /**
     * @return The Boolean of the specified Key
     */
     static boolean getBoolean(@NotNull Properties properties, @NotNull String key, boolean def) {
        try {
            return Boolean.parseBoolean(String.valueOf(getObject(properties, key, def)));
        }catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * @return The Integer of the specified Key
     */
     static int getInt(@NotNull Properties properties, @NotNull String key, int def) {
        try {
            return Integer.parseInt(String.valueOf(getObject(properties, key, def)));
        }catch(NumberFormatException e) {
            return 0;
        }
    }

    /**
     * @return The Double of the specified Key
     */
     static double getDouble(@NotNull Properties properties, @NotNull String key, double def) {
        try {
            return Double.parseDouble(String.valueOf(getObject(properties, key, def)));
        }catch(NumberFormatException e) {
            return 0D;
        }
    }

    /**
     * @return The Float of the specified Key
     */
     static float getFloat(@NotNull Properties properties, @NotNull String key, float def) {
        try {
            return Float.parseFloat(String.valueOf(getObject(properties, key, def)));
        }catch(NumberFormatException e) {
            return 0.0f;
        }
    }

    /**
     * @return The Object of the specified Key
     */
    @Nullable
     static Object getObject(@NotNull Properties properties, @NotNull String key, @NotNull Object def) {
        return properties.getProperty(key);
    }
}
