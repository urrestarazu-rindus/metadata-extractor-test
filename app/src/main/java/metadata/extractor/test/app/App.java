package metadata.extractor.test.app;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;

public class App {
    /**
     * metadata reader for videos
     * @param args args
     */
    public static void main(final String[] args) {

        System.out.println("Starting..");

        try {
            File videoFile = new File("/Users/alejandrou/development/metadata-extractor-test/app/src/main/resources/46735_1080x1920_6000 (1).mp4");

            if (videoFile.exists()) {
                Metadata metadata = ImageMetadataReader.readMetadata(videoFile);

                for (Directory directory : metadata.getDirectories()) {
                    for (Tag tag : directory.getTags()) {
                        System.out.format(String.format("[%s] - %s = %s",
                                directory.getName(), tag.getTagName(), tag.getDescription()));
                    }
                    if (directory.hasErrors()) {
                        for (String error : directory.getErrors()) {
                            System.err.format("ERROR: %s", error);
                        }
                    }
                }
            }

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
}
