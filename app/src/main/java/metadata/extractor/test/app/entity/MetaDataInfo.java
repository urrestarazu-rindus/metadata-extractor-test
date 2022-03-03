package metadata.extractor.test.app.entity;

public class MetaDataInfo {
    String format;
    //including ms HH:mm:ss ms / ms
    Long duration;
    //int height;
    //int width;
    Double framerate;
    String codec;
    int weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MetaDataInfo{" +
                "format='" + format + '\'' +
                ", duration= " + duration +
                ", framerate= " + framerate +
                ", codec='" + codec + '\'' +
                ", weight=" + weight +
                '}';
    }
}
