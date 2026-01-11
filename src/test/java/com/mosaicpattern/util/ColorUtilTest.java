package com.mosaicpattern.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ColorUtil.
 */
public class ColorUtilTest {

    @Test
    public void testRgbToHex() {
        assertEquals("#FF0000", ColorUtil.rgbToHex(255, 0, 0));
        assertEquals("#00FF00", ColorUtil.rgbToHex(0, 255, 0));
        assertEquals("#0000FF", ColorUtil.rgbToHex(0, 0, 255));
        assertEquals("#FFFFFF", ColorUtil.rgbToHex(255, 255, 255));
        assertEquals("#000000", ColorUtil.rgbToHex(0, 0, 0));
    }

    @Test
    public void testRgbToGrayscale() {
        int grayscale = ColorUtil.rgbToGrayscale(100, 100, 100);
        assertEquals(100, grayscale);

        // Test with different RGB values
        int grayscale2 = ColorUtil.rgbToGrayscale(255, 0, 0);
        assertTrue(grayscale2 > 0 && grayscale2 <= 255);
    }

    @Test
    public void testIsValidRGB() {
        assertTrue(ColorUtil.isValidRGB(0, 0, 0));
        assertTrue(ColorUtil.isValidRGB(255, 255, 255));
        assertTrue(ColorUtil.isValidRGB(128, 128, 128));
        
        assertFalse(ColorUtil.isValidRGB(-1, 0, 0));
        assertFalse(ColorUtil.isValidRGB(0, 256, 0));
        assertFalse(ColorUtil.isValidRGB(0, 0, 300));
    }
}
