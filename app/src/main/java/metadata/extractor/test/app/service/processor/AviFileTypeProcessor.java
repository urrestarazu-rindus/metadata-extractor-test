package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.avi.AviDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.AvailableFileType;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.Locale;

public class AviFileTypeProcessor implements FileTypeProcessor {
    @Override
    public MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo) {
        Directory aviDirectory = metadata.getFirstDirectoryOfType(AviDirectory.class);

        metaDataInfo.setFormat(AvailableFileType.AVI.getFileType());
        metaDataInfo.setCodec(aviDirectory.getDescription(AviDirectory.TAG_VIDEO_CODEC));

        Double frameRate = getFramerate(aviDirectory);
        metaDataInfo.setFramerate(frameRate);

        LocalTime localTime = LocalTime.parse(aviDirectory.getDescription(AviDirectory.TAG_DURATION));
        Long duration = localTime.toSecondOfDay() * 1000L;
        metaDataInfo.setDuration(duration);

        return metaDataInfo;
    }

    private Double getFramerate(Directory aviDirectory) {
        try {
            NumberFormat localeFormat = NumberFormat.getInstance(Locale.getDefault());
            Number number = localeFormat.parse(aviDirectory.getDescription(AviDirectory.TAG_FRAMES_PER_SECOND));
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
