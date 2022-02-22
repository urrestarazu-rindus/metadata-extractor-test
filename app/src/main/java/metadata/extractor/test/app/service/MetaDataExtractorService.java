package metadata.extractor.test.app.service;


import metadata.extractor.test.app.entity.MetaDataInfo;

public class MetaDataExtractorService {
    private String sourceURL;

    public MetaDataExtractorService(String url) {
        this.sourceURL = url;
    }

    public MetaDataInfo extract() {
        FileTypeProcessor fileTypeProcessor = ProcessorFactory.build(sourceURL);

        return fileTypeProcessor.execute();
    }
}
