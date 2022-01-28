package edu.mcw.scge.datamodel;

public class ExperimentResultDetail {

    private long resultId;

    private int numberOfSamples;
    private String units;
    private String assayDescription;
    private long experimentRecordId;
    private String experimentConditionName;
    private String resultType;
    private int replicate;
    private String result;
    private String editType;

    public String getExperimentConditionName() {
        return experimentConditionName;
    }

    public void setExperimentConditionName(String experimentConditionName) {
        this.experimentConditionName = experimentConditionName;
    }

    public long getExperimentRecordId() {
        return experimentRecordId;
    }

    public void setExperimentRecordId(long experimentRecordId) {
        this.experimentRecordId = experimentRecordId;
    }

    public long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
    }

    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    public void setNumberOfSamples(int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getAssayDescription() {
        return assayDescription;
    }

    public void setAssayDescription(String assayDescription) {
        this.assayDescription = assayDescription;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getReplicate() {
        return replicate;
    }

    public void setReplicate(int replicate) {
        this.replicate = replicate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }
}
