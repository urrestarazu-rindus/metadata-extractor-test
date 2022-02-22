package metadata.extractor.test.app;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.file.FileTypeDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.MetaDataExtractorService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class App {
    /**
     * metadata reader for videos.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        System.out.println("Starting..");
        try {
            //getMetaFromFile();


            Metadata urlMetadata = getMetaFromURL();

            MetaDataExtractorService service = new MetaDataExtractorService("https://file-examples-com.github.io/uploads/2018/04/file_example_AVI_480_750kB.avi");
            MetaDataInfo meta = service.extract();

            System.out.println();
            System.out.println(meta.toString());

            /*Directory fileTypeDirectory = urlMetadata.getFirstDirectoryOfType(FileTypeDirectory.class);

            System.out.println();
            System.out.println(
                    fileTypeDirectory.getTagName(FileTypeDirectory.TAG_DETECTED_FILE_TYPE_NAME) + " - " + fileTypeDirectory.getDescription(FileTypeDirectory.TAG_DETECTED_FILE_TYPE_NAME)
            );


            Directory mp4VideoDirectory = urlMetadata.getFirstDirectoryOfType(Mp4VideoDirectory.class);
            System.out.println(
                    mp4VideoDirectory.getTagName(Mp4VideoDirectory.TAG_COMPRESSION_TYPE) + " - " + mp4VideoDirectory.getDescription(Mp4VideoDirectory.TAG_COMPRESSION_TYPE)
            );
            System.out.println(
                    mp4VideoDirectory.getTagName(Mp4VideoDirectory.TAG_FRAME_RATE) + " - " + mp4VideoDirectory.getDescription(Mp4VideoDirectory.TAG_FRAME_RATE)
            );*/

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    private static Metadata getMetaFromURL() throws IOException, ImageProcessingException {
        //InputStream inputStream = new URL("https://cs.adscale.de/2089489571/46735_1080x1920_6000.mp4").openStream();

        //URL url = new URL("https://cs.adscale.de/2089489571/46735_1080x1920_6000.mp4");
        //URL url = new URL("https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png");
        URL url = new URL("https://file-examples-com.github.io/uploads/2018/04/file_example_AVI_480_750kB.avi");

        URLConnection conn = null;
        conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        int weight = conn.getContentLength();

        Metadata urlMetadata = ImageMetadataReader.readMetadata(inputStream);
        //showMetadata(urlMetadata);
        return urlMetadata;
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
