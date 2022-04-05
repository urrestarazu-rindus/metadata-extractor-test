package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.media.QuickTimeVideoDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.AvailableFileType;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MovFileTypeProcessor implements FileTypeProcessor {
    @Override
    public MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo) {
        String format = null;
        Long duration = null;
        Double framerate = null;
        String codec = null;
        int timeScale;

        if (metadata == null) {
            return metaDataInfo;
        }

        Directory qtDirectory = metadata.getFirstDirectoryOfType(QuickTimeDirectory.class);
        Directory qtVideoDirectory = metadata.getFirstDirectoryOfType(QuickTimeVideoDirectory.class);

        if (qtDirectory != null){
            format = AvailableFileType.MOV.getFileType();
            duration = Long.parseLong(qtDirectory.getDescription(QuickTimeDirectory.TAG_DURATION));
            timeScale = (duration > 0)? Integer.parseInt(qtDirectory.getDescription(QuickTimeDirectory.TAG_TIME_SCALE)) : 1;
            duration = (1000L * duration) / timeScale;
            if (qtVideoDirectory!= null) {
                framerate = getFramerate(framerate, qtVideoDirectory);
                codec = qtVideoDirectory.getDescription(QuickTimeVideoDirectory.TAG_COMPRESSION_TYPE);
            }
        }

        return new MetaDataInfo(format
                ,duration
                ,framerate
                ,codec
                ,metaDataInfo.getWeight()
        );
    }

    private Double getFramerate(Double framerate, Directory qtVideoDirectory) {
        try {
            NumberFormat localeFormat = NumberFormat.getInstance(Locale.getDefault());
            Number number = localeFormat.parse(qtVideoDirectory.getDescription(QuickTimeVideoDirectory.TAG_FRAME_RATE));
            framerate = number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return framerate;
    }
}
