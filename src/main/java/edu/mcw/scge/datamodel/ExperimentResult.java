package edu.mcw.scge.datamodel;

public class ExperimentResult {

    private int resultId;
    private String tissueId;
    private String cellType;
    private int numberOfSamples;
    private String units;
    private String assayDescription;
    private int experimentRecordId;
    private String resultType;


    public int getExperimentRecordId() {
        return experimentRecordId;
    }

    public void setExperimentRecordId(int experimentRecordId) {
        this.experimentRecordId = experimentRecordId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
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
}
