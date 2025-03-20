package com.xiaoming.Spring;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import java.io.File;
import java.io.IOException;

public class MetadataReader {
    public static void main(String[] args) {
        File dicomFile = new File("src/main/resources//uploads/3.dcm");

        try (DicomInputStream dis = new DicomInputStream(dicomFile)) {
            Attributes dataset = dis.readDataset();

            System.out.println("=== DICOM Metadata ===");
            System.out.printf("Patient Name: %s\n", dataset.getString(Tag.PatientName));
            System.out.printf("Patient ID: %s\n", dataset.getString(Tag.PatientID));
            System.out.printf("Study Date: %s\n", dataset.getDate(Tag.StudyDate));
            System.out.printf("Modality: %s\n", dataset.getString(Tag.Modality));
            System.out.printf("Image Size: %dx%d\n",
                    dataset.getInt(Tag.Columns, 0),
                    dataset.getInt(Tag.Rows, 0));

        } catch (IOException e) {
            System.err.println("Error reading DICOM file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
