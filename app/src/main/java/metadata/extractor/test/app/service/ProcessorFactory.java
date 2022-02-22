package metadata.extractor.test.app.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.file.FileTypeDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ProcessorFactory {

    static FileTypeProcessor build(String sourceURL) {
        try {
            URL url = new URL(sourceURL);
            URLConnection conn = null;
            conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            Metadata metadata = metadata = ImageMetadataReader.readMetadata(inputStream);
            showMetadata(metadata);

            MetaDataInfo metaDataInfo = new MetaDataInfo();
            metaDataInfo.setWeight(conn.getContentLength());


            //Switch
            Directory fileTypeDirectory = metadata.getFirstDirectoryOfType(FileTypeDirectory.class);
            String fileTypeName = fileTypeDirectory.getDescription(FileTypeDirectory.TAG_DETECTED_FILE_TYPE_NAME);
            metaDataInfo.setFormat(fileTypeName);

            switch (fileTypeName) {
                case "AVI":
                    return new AviFileTypeProcessor(metadata, metaDataInfo);
                default:
                    return null;
            }
        } catch (Exception exception) {

        }
        return null;
    }

    //Borrar
    private static void showMetadata(Metadata metadata) {
        System.out.println("\n--- metadata ---");
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format(String.format("\n[%s]\t - %s = %s",
                        directory.getName(),
                        tag.getTagName(),
                        tag.getDescription()));
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
    }
}

