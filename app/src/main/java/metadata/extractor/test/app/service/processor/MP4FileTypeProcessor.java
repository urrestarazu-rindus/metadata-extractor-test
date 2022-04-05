package metadata.extractor.test.app.service.processor;

import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4Directory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import metadata.extractor.test.app.entity.MetaDataInfo;
import metadata.extractor.test.app.service.AvailableFileType;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MP4FileTypeProcessor implements FileTypeProcessor {

    @Override
    public MetaDataInfo execute(Metadata metadata, MetaDataInfo metaDataInfo) {
        long duration;
        int timeScale;

        Directory mp4VideoDirectory = metadata.getFirstDirectoryOfType(Mp4VideoDirectory.class);

        metaDataInfo.setFormat(AvailableFileType.MP4.getFileType());
        metaDataInfo.setCodec(mp4VideoDirectory.getDescription(Mp4VideoDirectory.TAG_COMPRESSION_TYPE));
        metaDataInfo.setFramerate(getFormatedFrameRate(mp4VideoDirectory.getDescription(Mp4VideoDirectory.TAG_FRAME_RATE)));

        Directory mp4Directory = metadata.getFirstDirectoryOfType(Mp4Directory.class);

        duration = Long.parseLong(mp4Directory.getDescription(Mp4Directory.TAG_DURATION));
        timeScale = (duration > 0)? Integer.parseInt(mp4Directory.getDescription(Mp4Directory.TAG_TIME_SCALE)) : 1;
        metaDataInfo.setDuration((1000L * duration) / timeScale);

        return metaDataInfo;
    }

    private Double getFormatedFrameRate(String frameRate) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            Number framerate = format.parse(frameRate);
            return framerate.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
