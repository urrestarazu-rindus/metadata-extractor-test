package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.jpeg.JpegDirectory;
import com.drew.metadata.png.PngDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.AvailableFileType;

public class PngFileTypeProcessor implements FileTypeProcessor {
    @Override
    public MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo) {
        Directory pngDirectory = metadata.getFirstDirectoryOfType(PngDirectory.class);

        metaDataInfo.setFormat(AvailableFileType.PNG.getFileType());
        metaDataInfo.setDuration(0L);
        return metaDataInfo;
    }
}
