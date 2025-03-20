package com.xiaoming.Spring;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageReader;

public class DicomConverter {
    public static void main(String[] args) {
        String inputPath = "src/main/resources//uploads/3.dcm";
        String outputPath = "converted_image.png";

        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("DICOM");
            if (!readers.hasNext()) {
                throw new RuntimeException("No DICOM ImageReader found");
            }

            ImageReader reader = readers.next();
            reader.setInput(new FileImageInputStream(new File(inputPath)));

            BufferedImage image = reader.read(0);
            ImageIO.write(image, "PNG", new File(outputPath));

            System.out.println("Conversion successful: " + outputPath);
            new ImageViewer(image).setVisible(true);

        } catch (IOException e) {
            System.err.println("Conversion failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
