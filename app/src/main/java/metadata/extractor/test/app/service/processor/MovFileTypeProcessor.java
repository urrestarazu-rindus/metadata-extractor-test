package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.media.QuickTimeVideoDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.AvailableFileType;

public class MovFileTypeProcessor implements FileTypeProcessor {
    @Override
    public MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo) {
        Directory qtVideoDirectory = metadata.getFirstDirectoryOfType(QuickTimeVideoDirectory.class);

        metaDataInfo.setFormat(AvailableFileType.AVI.getFileType());
        metaDataInfo.setCodec(qtVideoDirectory.getDescription(QuickTimeVideoDirectory.TAG_COMPRESSION_TYPE));
        metaDataInfo.setFramerate(Double.parseDouble(qtVideoDirectory.getDescription(QuickTimeVideoDirectory.TAG_FRAME_RATE)));
        metaDataInfo.setDuration(Long.parseLong(qtVideoDirectory.getDescription(QuickTimeDirectory.TAG_DURATION)));

        return metaDataInfo;
    }
}
