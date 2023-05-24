package me.xra1ny.gameapi.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public interface FileUtils {
    /**
     * attempts to create the file specified
     *
     * @return true if the file was created successfully, false otherwise
     */
     static boolean create(@NotNull File file) {
        try {
            final File parentFile = file.getParentFile();
            if(parentFile != null) {
                parentFile.mkdirs();
            }
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * retrieves the input stream of the file specified
     *
     * @return the input stream on success, null otherwise
     */
    @Nullable
     static InputStream getInputStream(@NotNull File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * retrieves the output stream of the file specified
     *
     * @return the output stream on success, null otherwise
     */
    @Nullable
     static OutputStream getOutputStream(@NotNull File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
