package com.csh.mosaicpattern.backend;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * Service class for processing and manipulating images.
 */
public class ImageProcessor {

    /**
     * Converts the given image to grayscale.
     *
     * @param image the input image
     * @return the grayscale image
     */
    public BufferedImage convertToGrayscale(BufferedImage image) {
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        return op.filter(image, null);
    }
}
