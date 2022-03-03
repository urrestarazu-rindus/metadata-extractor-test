package metadata.extractor.test.app.service;

import metadata.extractor.test.app.service.processor.AviFileTypeProcessor;
import metadata.extractor.test.app.service.processor.FileTypeProcessor;
import metadata.extractor.test.app.service.processor.MP4FileTypeProcessor;
import metadata.extractor.test.app.service.processor.MovFileTypeProcessor;

public enum AvailableFileType {
    MP4("MP4", new MP4FileTypeProcessor()),
    AVI("AVI", new AviFileTypeProcessor()),
    MOV("MOV", new MovFileTypeProcessor());

    private final FileTypeProcessor processor;
    private final String fileType;

    AvailableFileType(String fileType, FileTypeProcessor processor) {
        this.fileType = fileType;
        this.processor = processor;
    }

    public static FileTypeProcessor findProcessor(String fileType) {
        for (AvailableFileType availableFileType : AvailableFileType.values()) {
            if (availableFileType.fileType.equalsIgnoreCase(fileType)) {
                return availableFileType.processor;
            }
        }

        return null;
    }

    public FileTypeProcessor getProcessor() {
        return processor;
    }

    public String getFileType() {
        return fileType;
    }
}
