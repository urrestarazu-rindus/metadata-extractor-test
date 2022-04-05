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

public class App {
    /**
     * metadata reader for videos.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        System.out.println("Starting..");
        try {
            //MetaDataExtractorService service = new MetaDataExtractorService("https://filesamples.com/samples/video/avi/sample_960x400_ocean_with_audio.avi");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://cs.adscale.de/2089489571/46735_1080x1920_6000.mp4");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://s3-ap-southeast-2.amazonaws.com/adscale-test-ads/advert2.mp4");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://img.taste.com.au/Hp5jidww/w720-h480-cfill-q80/taste/2016/11/pan-seared-salmon-with-smashed-peas-89698-1.jpeg");
            MetaDataExtractorService service = new MetaDataExtractorService("https://static.sage-archer.com/paas/13405/58762/6a965131-550a-4c55-8759-4a541dd245db.mp4");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://filesamples.com/samples/video/mov/sample_640x360.mov");
            //MetaDataExtractorService service = new MetaDataExtractorService("https://static.sage-archer.com/paas/13194/58375/935ec4a5-b257-4f5f-b955-53d1aa43a3c1.jpg");
            MetaDataInfo meta = service.extract();

            System.out.println();
            System.out.println(meta.toString());

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
