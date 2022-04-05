package metadata.extractor.test.app.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MetaDataInfo {
    public MetaDataInfo() {
    }

    public MetaDataInfo(String format, Long duration, Double framerate, String codec, Double weight) {
        this.format = format;
        this.duration = duration;
        this.framerate = framerate;
        this.codec = codec;
        this.weight = weight;
    }

    String format;
    //including ms HH:mm:ss ms / ms
    Long duration;
    //int height;
    //int width;
    Double framerate;
    String codec;
    Double weight;
    // MBit/s
    //int bitrate;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getFramerate() {
        return framerate;
    }

    public void setFramerate(Double framerate) {
        this.framerate = framerate;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");
        LocalDateTime ldt = Instant.ofEpochMilli(this.getDuration())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        //System.out.println("Formated duration: " + ldt.format(formatter));

        return "MetaDataInfo {" +
                "format='" + format + '\'' +
                ", duration= " + ldt.format(formatter) +
                ", framerate= " + framerate +
                ", codec='" + codec + '\'' +
                ", weight=" + weight +
                '}';
    }
}
