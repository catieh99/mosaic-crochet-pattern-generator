package com.csh.mosaicpattern.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for ColorUtil.
 */
class ColorUtilTest {

    @Test
    void testRgbToHex() {
        assertEquals("#FF0000", ColorUtil.rgbToHex(255, 0, 0));
        assertEquals("#00FF00", ColorUtil.rgbToHex(0, 255, 0));
        assertEquals("#0000FF", ColorUtil.rgbToHex(0, 0, 255));
        assertEquals("#FFFFFF", ColorUtil.rgbToHex(255, 255, 255));
        assertEquals("#000000", ColorUtil.rgbToHex(0, 0, 0));
    }

    @Test
    void testRgbToGrayscale() {
        int grayscale = ColorUtil.rgbToGrayscale(100, 100, 100);
        assertEquals(100, grayscale);

        // Test with different RGB values
        int grayscale2 = ColorUtil.rgbToGrayscale(255, 0, 0);
        assertTrue(grayscale2 > 0 && grayscale2 <= 255);
    }

    @Test
    void testIsValidRGB() {
        assertTrue(ColorUtil.isValidRGB(0, 0, 0));
        assertTrue(ColorUtil.isValidRGB(255, 255, 255));
        assertTrue(ColorUtil.isValidRGB(128, 128, 128));

        assertFalse(ColorUtil.isValidRGB(-1, 0, 0));
        assertFalse(ColorUtil.isValidRGB(0, 256, 0));
        assertFalse(ColorUtil.isValidRGB(0, 0, 300));
    }

    @Test
    void testRgbToHexWithInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> ColorUtil.rgbToHex(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> ColorUtil.rgbToHex(0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> ColorUtil.rgbToHex(0, 0, 300));
    }

    @Test
    void testRgbToGrayscaleWithInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> ColorUtil.rgbToGrayscale(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> ColorUtil.rgbToGrayscale(0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> ColorUtil.rgbToGrayscale(0, 0, 300));
    }
}
