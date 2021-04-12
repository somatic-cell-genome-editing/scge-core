package edu.mcw.scge.datamodel;

public class ExperimentRecord {
    private int experimentRecordId;
    private int experimentId;
    private String experimentName;
    private String experimentType;
    private int studyId;
    private int editorId;
    private int deliverySystemId;
    private int vectorId;
    private int modelId;
    private int guideId;
    private String samplePrep;
    private int applicationMethodId;
    private String editorSymbol;
    private String deliverySystemType;
    private String guide;
    private String modelName;
    private String studyName;
    private String age;
    private String genotype;
    private String sex;
    private String tissueId;
    private String cellType;
    private String vector;

    public int getExperimentRecordId() {
        return experimentRecordId;
    }

    public void setExperimentRecordId(int experimentRecordId) {
        this.experimentRecordId = experimentRecordId;
    }

    public int getStudyId() {
        return studyId;
    }

    public void setStudyId(int studyId) {
        this.studyId = studyId;
    }

    public int getDeliverySystemId() {
        return deliverySystemId;
    }

    public void setDeliverySystemId(int deliverySystemId) {
        this.deliverySystemId = deliverySystemId;
    }

    public int getVectorId() {
        return vectorId;
    }

    public void setVectorId(int vectorId) {
        this.vectorId = vectorId;
    }

    public String getEditorSymbol() {
        return editorSymbol;
    }

    public void setEditorSymbol(String editorSymbol) {
        this.editorSymbol = editorSymbol;
    }

    public String getDeliverySystemType() {
        return deliverySystemType;
    }

    public void setDeliverySystemType(String deliverySystemType) {
        this.deliverySystemType = deliverySystemType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(String experimentType) {
        this.experimentType = experimentType;
    }

    public String getTissueId() {
        return tissueId;
    }

    public void setTissueId(String tissueId) {
        this.tissueId = tissueId;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }
}
