package edu.mcw.scge.datamodel;

public class Guide  {
    private int guide_id;
    private String guide;
    private int sequence_id;
    private String gRnaFormat;
    private String detectionMethod;

    public int getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public int getSequence_id() {
        return sequence_id;
    }

    public void setSequence_id(int sequence_id) {
        this.sequence_id = sequence_id;
    }

    public String getgRnaFormat() {
        return gRnaFormat;
    }

    public void setgRnaFormat(String gRnaFormat) {
        this.gRnaFormat = gRnaFormat;
    }

    public String getDetectionMethod() {
        return detectionMethod;
    }

    public void setDetectionMethod(String detectionMethod) {
        this.detectionMethod = detectionMethod;
    }
}
