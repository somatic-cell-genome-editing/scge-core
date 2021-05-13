package edu.mcw.scge.datamodel;


public class OffTarget {

    private int guideId;
    private String detectionMethod;
    private int noOfSitesDetected;

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public String getDetectionMethod() {
        return detectionMethod;
    }

    public void setDetectionMethod(String detectionMethod) {
        this.detectionMethod = detectionMethod;
    }

    public int getNoOfSitesDetected() {
        return noOfSitesDetected;
    }

    public void setNoOfSitesDetected(int noOfSitesDetected) {
        this.noOfSitesDetected = noOfSitesDetected;
    }
}
