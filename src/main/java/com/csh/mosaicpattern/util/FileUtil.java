package com.csh.mosaicpattern.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.experimental.UtilityClass;

/**
 * Utility class for file operations.
 */
@UtilityClass
public class FileUtil {

    /**
     * Reads the contents of a file into a byte array.
     *
     * @param filePath the path to the file
     * @return the file contents as a byte array
     * @throws IOException              if an I/O error occurs
     * @throws IllegalArgumentException if filePath is null or empty
     */
    public static byte[] readFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        Path path = Path.of(filePath);
        return Files.readAllBytes(path);
    }

    /**
     * Writes a byte array to a file.
     *
     * @param filePath the path to the file
     * @param data     the data to write
     * @throws IOException              if an I/O error occurs
     * @throws IllegalArgumentException if filePath is null or empty, or if data is
     *                                  null
     */
    public static void writeFile(String filePath, byte[] data) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        Path path = Path.of(filePath);
        Files.write(path, data);
    }

    /**
     * Checks if a file exists at the given path.
     *
     * @param filePath the path to check
     * @return true if the file exists, false otherwise
     */
    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }
}
