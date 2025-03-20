package com.xiaoming.Spring;
import org.dcm4che3.data.*;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;
import java.io.File;
import java.io.IOException;

public class TagEditor {

    private static final String MY_IMPLEMENTATION_UID = "1.2.40.0.13.1.1.1";
    public static void main(String[] args) {

        File srcFile = new File("src/main/resources/uploads/3.dcm");
        File destFile = new File("modified.dcm");

        try (DicomInputStream dis = new DicomInputStream(srcFile);
             DicomOutputStream dos = new DicomOutputStream(destFile)) {

            // 读取原始数据集
            Attributes dataset = dis.readDataset();

            // 设置传输语法（重要！）
            String transferSyntax = UID.ExplicitVRLittleEndian;
            dataset.setString(Tag.TransferSyntaxUID, VR.UI, transferSyntax);

            // 创建文件元信息
            Attributes fmi = new Attributes(7);
            fmi.setString(Tag.MediaStorageSOPClassUID, VR.UI,
                    dataset.getString(Tag.SOPClassUID));
            fmi.setString(Tag.MediaStorageSOPInstanceUID, VR.UI,
                    dataset.getString(Tag.SOPInstanceUID));
            fmi.setString(Tag.TransferSyntaxUID, VR.UI, transferSyntax);
            fmi.setString(Tag.ImplementationClassUID, VR.UI,
                    MY_IMPLEMENTATION_UID);

            // 修改患者信息
            dataset.setString(Tag.PatientName, VR.PN, "Anonymous^Patient");
            dataset.setString(Tag.PatientID, VR.LO, "ID_123456");

            // 写入文件
            dos.writeFileMetaInformation(fmi);
            dos.writeDataset(dataset.createFileMetaInformation(transferSyntax), dataset);

            System.out.println("DICOM tags modified successfully!");

        } catch (IOException e) {
            System.err.println("Error editing DICOM: " + e.getMessage());
            e.printStackTrace();
        }
    }
}