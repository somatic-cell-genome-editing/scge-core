package edu.mcw.scge.datamodel;

public class ReporterElement {
    private int reporterElementId;
    private int experimentRecordId;
    private String reporterName;
    private String reporterType;
    private String reporterProteinId;
    private String organism;

    public int getExperimentRecordId() {
        return experimentRecordId;
    }

    public void setExperimentRecordId(int experimentRecordId) {
        this.experimentRecordId = experimentRecordId;
    }

    public int getReporterElementId() {
        return reporterElementId;
    }

    public void setReporterElementId(int reporterElementId) {
        this.reporterElementId = reporterElementId;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterType() {
        return reporterType;
    }

    public void setReporterType(String reporterType) {
        this.reporterType = reporterType;
    }

    public String getReporterProteinId() {
        return reporterProteinId;
    }

    public void setReporterProteinId(String reporterProteinId) {
        this.reporterProteinId = reporterProteinId;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }
}
