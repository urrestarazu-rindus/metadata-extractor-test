package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.jpeg.JpegDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.AvailableFileType;

public class JpegFileTypeProcessor implements FileTypeProcessor {
    @Override
    public MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo) {
        Directory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);

        metaDataInfo.setFormat(AvailableFileType.JPEG.getFileType());
        metaDataInfo.setDuration(0L);
        return metaDataInfo;
    }
}
