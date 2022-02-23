package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.AvailableFileType;

public class MP4FileTypeProcessor implements FileTypeProcessor {

    @Override
    public MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo) {
        Directory mp4VideoDirectory = metadata.getFirstDirectoryOfType(Mp4VideoDirectory.class);

        metaDataInfo.setFormat(AvailableFileType.MP4.getFileType());
        metaDataInfo.setCodec(mp4VideoDirectory.getDescription(Mp4VideoDirectory.TAG_COMPRESSION_TYPE));
        metaDataInfo.setFramerate(Double.parseDouble(mp4VideoDirectory.getDescription(Mp4VideoDirectory.TAG_FRAME_RATE)));

        return metaDataInfo;
    }
}
