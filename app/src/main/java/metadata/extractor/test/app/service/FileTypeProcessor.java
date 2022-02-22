package metadata.extractor.test.app.service;

import com.drew.metadata.Metadata;
import metadata.extractor.test.app.entity.MetaDataInfo;

public interface FileTypeProcessor {
    MetaDataInfo execute();
}
