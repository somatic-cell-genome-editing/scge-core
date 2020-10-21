package edu.mcw.scge.datamodel;

public class ExperimentRecord {
    private int experimentRecId;
    private int experimentId;
    private String experimentName;
    private int editorId;
    private String editorType;
    private String editorName;
    private int deliveryId;
    private String deliveryType;
    private String deliveyName;
    private int modelId;
    private String model;
    private int guideId;
    private String guide;
    private String guideSequence;
    private int targetId;
    private String targetLocusSymbol;
    private String targetLocus;
    private String targetSequence;
    private String guideDetectionMethod;
    private double specificity;
    private String samplePrep;
    private int applicationMethodId;

    public int getExperimentRecId() {
        return experimentRecId;
    }

    public void setExperimentRecId(int experimentRecId) {
        this.experimentRecId = experimentRecId;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public String getEditorType() {
        return editorType;
    }

    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveyName() {
        return deliveyName;
    }

    public void setDeliveyName(String deliveyName) {
        this.deliveyName = deliveyName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getGuideSequence() {
        return guideSequence;
    }

    public void setGuideSequence(String guideSequence) {
        this.guideSequence = guideSequence;
    }

    public String getTargetLocusSymbol() {
        return targetLocusSymbol;
    }

    public void setTargetLocusSymbol(String targetLocusSymbol) {
        this.targetLocusSymbol = targetLocusSymbol;
    }

    public String getTargetLocus() {
        return targetLocus;
    }

    public void setTargetLocus(String targetLocus) {
        this.targetLocus = targetLocus;
    }

    public String getTargetSequence() {
        return targetSequence;
    }

    public void setTargetSequence(String targetSequence) {
        this.targetSequence = targetSequence;
    }

    public String getGuideDetectionMethod() {
        return guideDetectionMethod;
    }

    public void setGuideDetectionMethod(String guideDetectionMethod) {
        this.guideDetectionMethod = guideDetectionMethod;
    }

    public double getSpecificity() {
        return specificity;
    }

    public void setSpecificity(double specificity) {
        this.specificity = specificity;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
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

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
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
