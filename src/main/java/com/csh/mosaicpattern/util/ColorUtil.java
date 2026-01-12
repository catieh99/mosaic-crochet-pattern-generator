package com.csh.mosaicpattern.util;

import javafx.scene.paint.Color;
import lombok.experimental.UtilityClass;

/**
 * Utility class for color conversions and manipulations.
 */
@UtilityClass
public class ColorUtil {

    /**
     * Converts RGB values to a hex color string.
     *
     * @param red   the red component (0-255)
     * @param green the green component (0-255)
     * @param blue  the blue component (0-255)
     * @return the hex color string (e.g., "#FF0000" for red)
     * @throws IllegalArgumentException if any RGB value is out of range [0, 255]
     */
    public static String rgbToHex(int red, int green, int blue) {
        if (!isValidRGB(red, green, blue)) {
            throw new IllegalArgumentException("RGB values must be in range [0, 255]");
        }
        return String.format("#%02X%02X%02X", red, green, blue);
    }

    /**
     * Calculates the grayscale value from RGB components.
     *
     * @param red   the red component (0-255)
     * @param green the green component (0-255)
     * @param blue  the blue component (0-255)
     * @return the grayscale value (0-255)
     * @throws IllegalArgumentException if any RGB value is out of range [0, 255]
     */
    public static int rgbToGrayscale(int red, int green, int blue) {
        if (!isValidRGB(red, green, blue)) {
            throw new IllegalArgumentException("RGB values must be in range [0, 255]");
        }
        return (int) (0.299 * red + 0.587 * green + 0.114 * blue);
    }

    /**
     * Validates if RGB values are within the valid range.
     *
     * @param red   the red component
     * @param green the green component
     * @param blue  the blue component
     * @return true if all values are in range [0, 255], false otherwise
     */
    public static boolean isValidRGB(int red, int green, int blue) {
        return red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255;
    }

    public String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public String toRgbString(Color color) {
        return String.format("%d, %d, %d",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
