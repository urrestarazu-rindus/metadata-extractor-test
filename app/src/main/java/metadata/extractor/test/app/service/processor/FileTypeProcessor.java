package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Metadata;
import metadata.extractor.test.app.entity.MetaDataInfo;

public interface FileTypeProcessor {
    MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo);
}
