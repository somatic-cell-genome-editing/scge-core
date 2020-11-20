package edu.mcw.scge.datamodel;


public class Experiment {
    private int experimentId;
    private String experimentName;
    private int studyId;
    private int editorId;
    private int deliverySystemId;
    private int modelId;
    private int guideId;
    private String samplePrep;
    private int applicationMethodId;

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public int getStudyId() {
        return studyId;
    }

    public void setStudyId(int studyId) {
        this.studyId = studyId;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public int getDeliverySystemId() {
        return deliverySystemId;
    }

    public void setDeliverySystemId(int deliverySystemId) {
        this.deliverySystemId = deliverySystemId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public String getSamplePrep() {
        return samplePrep;
    }

    public void setSamplePrep(String samplePrep) {
        this.samplePrep = samplePrep;
    }

    public int getApplicationMethodId() {
        return applicationMethodId;
    }

    public void setApplicationMethodId(int applicationMethodId) {
        this.applicationMethodId = applicationMethodId;
    }
}
