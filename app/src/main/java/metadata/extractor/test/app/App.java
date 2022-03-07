package metadata.extractor.test.app;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.MetaDataExtractorService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class App {
    /**
     * metadata reader for videos.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        System.out.println("Starting..");
        try {
            //MetaDataExtractorService service = new MetaDataExtractorService("https://file-examples-com.github.io/uploads/2018/04/file_example_AVI_480_750kB.avi");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://cs.adscale.de/2089489571/46735_1080x1920_6000.mp4");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://s3-ap-southeast-2.amazonaws.com/adscale-test-ads/advert2.mp4");
            MetaDataExtractorService service = new MetaDataExtractorService("https://cd.adition.com/assets/adition/3145/22/02/03/14/19/47031_1920x1080_3000.mp4");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://file-examples-com.github.io/uploads/2018/04/file_example_MOV_480_700kB.mov");
            MetaDataInfo meta = service.extract();

            System.out.println();
            System.out.println(meta.toString());

            LocalDateTime dateTime = Instant.ofEpochMilli(meta.getDuration())
                    .atZone(ZoneId.systemDefault()) // default zone
                    .toLocalDateTime();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

            System.out.println(formatter.format(dateTime));

            //getMetaFromFile();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    private static void getMetaFromFile() throws ImageProcessingException, IOException {
        File videoFile = new File("/Users/alejandrou/development/metadata-extractor-test/app/src/main/resources/46735_1080x1920_6000 (1).mp4");

        if (videoFile.exists()) {
            Metadata metadata = ImageMetadataReader.readMetadata(videoFile);
            //showMetadata(metadata);
        }
    }

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
