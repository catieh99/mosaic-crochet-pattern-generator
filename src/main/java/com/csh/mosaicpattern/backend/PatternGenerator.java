package com.csh.mosaicpattern.backend;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Service class for processing images and generating mosaic crochet patterns.
 */
public class PatternGenerator extends Task<Image> {

    BufferedImage inputImage;
    Color color1;
    Color color2;

    public PatternGenerator(BufferedImage inputImage, Color color1, Color color2) {
        this.inputImage = inputImage;
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    protected Image call() throws Exception {
        // Compress image if needed
        BufferedImage compressedImage = compressImage(inputImage, 250, 250);

        // Calculate average gray value for image
        ArrayList<ArrayList<Integer>> grayValues = new ArrayList<>();
        for (int y = 0; y < compressedImage.getHeight(); y++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int x = 0; x < compressedImage.getWidth(); x++) {
                int rgb = compressedImage.getRGB(x, y);
                java.awt.Color awtColor = new java.awt.Color(rgb);
                int gray = (int) ((0.299 * awtColor.getRed()) + (0.587 * awtColor.getGreen())
                        + (0.114 * awtColor.getBlue()));

                row.add(gray);
            }
            grayValues.add(row);
        }

        float avgGray = (float) grayValues.stream()
                .flatMapToInt(row -> row.stream().mapToInt(Integer::intValue))
                .average()
                .orElse(128);

        // Calculate whether color 1 or color 2 is closer for each pixel
        for (int y = 0; y < grayValues.size(); y++) {
            for (int x = 0; x < grayValues.get(y).size(); x++) {
                int gray = grayValues.get(y).get(x);

                // Thresholding with average gray value to decide between color1 and color2
                if (gray < avgGray) {
                    compressedImage.setRGB(x, y, new java.awt.Color(
                            (int) (color1.getRed() * 255),
                            (int) (color1.getGreen() * 255),
                            (int) (color1.getBlue() * 255)).getRGB());
                } else {
                    compressedImage.setRGB(x, y, new java.awt.Color(
                            (int) (color2.getRed() * 255),
                            (int) (color2.getGreen() * 255),
                            (int) (color2.getBlue() * 255)).getRGB());
                }
            }
        }

        // Return new image based on calculations
        return compressedImage != null ? SwingFXUtils.toFXImage(compressedImage, null) : null;
    }

    private BufferedImage compressImage(BufferedImage image, int maxWidth, int maxHeight) {
        int width = image.getWidth();
        int height = image.getHeight();

        double widthRatio = (double) maxWidth / width;
        double heightRatio = (double) maxHeight / height;
        double ratio = Math.min(widthRatio, heightRatio);

        if (ratio >= 1.0) {
            return image; // No need to resize
        }

        int newWidth = (int) (width * ratio);
        int newHeight = (int) (height * ratio);

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());
        java.awt.Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return resizedImage;
    }

}
