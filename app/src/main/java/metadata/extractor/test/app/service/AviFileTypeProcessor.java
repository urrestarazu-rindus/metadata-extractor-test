package metadata.extractor.test.app.service;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.avi.AviDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;

public class AviFileTypeProcessor implements FileTypeProcessor {
    private final Metadata metaData;
    protected MetaDataInfo metaDataInfo;

    public AviFileTypeProcessor(Metadata metadata, MetaDataInfo metaDataInfo) {
        this.metaData = metadata;
        this.metaDataInfo = metaDataInfo;
    }

    @Override
    public MetaDataInfo execute() {
        Directory aviDirectory = this.metaData.getFirstDirectoryOfType(AviDirectory.class);
        metaDataInfo.setCodec(aviDirectory.getDescription(AviDirectory.TAG_VIDEO_CODEC));
        metaDataInfo.setFramerate(Double.parseDouble(aviDirectory.getDescription(AviDirectory.TAG_FRAMES_PER_SECOND)));

        return metaDataInfo;
    }
}
