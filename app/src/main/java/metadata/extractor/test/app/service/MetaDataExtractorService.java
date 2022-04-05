package metadata.extractor.test.app.service;


import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.file.FileTypeDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.processor.FileTypeProcessor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MetaDataExtractorService {
    private final double SIZE_MB = Math.pow(1024, 2);

    private String sourceURL;

    public MetaDataExtractorService(String url) {
        this.sourceURL = url;
    }

    public MetaDataInfo extract() {
        try {
            double weight = 0.0;
            Metadata metadata = null;

            URL dspFileUrl = new URL(sourceURL);
            HttpURLConnection connection = (HttpURLConnection) dspFileUrl.openConnection();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream urlStream = dspFileUrl.openStream();
                byte[] byteArray = urlStream.readAllBytes();

                String strBytes = new String(byteArray, StandardCharsets.ISO_8859_1);

                weight = byteArray.length / SIZE_MB;
                InputStream targetStream = new ByteArrayInputStream(byteArray);

                metadata = ImageMetadataReader.readMetadata(targetStream);

                showMetadata(metadata);

                urlStream.close();
            }
            //-------

            MetaDataInfo metaDataInfo = new MetaDataInfo();
            metaDataInfo.setWeight(weight);

            //Switch
            Directory fileTypeDirectory = metadata.getFirstDirectoryOfType(FileTypeDirectory.class);
            String fileTypeName = fileTypeDirectory.getDescription(FileTypeDirectory.TAG_DETECTED_FILE_TYPE_NAME);


            FileTypeProcessor processor = AvailableFileType.findProcessor(fileTypeName);

            return processor.execute(metadata, metaDataInfo);

        } catch (Exception exception) {
            System.out.println("Error: MetaDataExtractorService: " + exception.getMessage());
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
        System.out.println();
    }
}
