package edu.mcw.scge.datamodel;

public class AnimalTestingResultsSummary {
    private int summaryResultsId;
     private int       expRecId;
    private int numberOfSamples;
    private int maleSamples;
    private int femaleSamples;
    private int tissueId;
    private String tissueAccId;
    private String tissueTerm;
    private String parentTissueTerm;
    private String system;
    private String  signal;
    private String signalPresent;
    private String signalDescription;
    private String imageLink;
      private String      percentCellsInROIWithSginal;
    private String roi;
    private String ROICoordinates;


    public int getMaleSamples() {
        return maleSamples;
    }

    public void setMaleSamples(int maleSamples) {
        this.maleSamples = maleSamples;
    }

    public int getFemaleSamples() {
        return femaleSamples;
    }

    public int getSummaryResultsId() {
        return summaryResultsId;
    }

    public void setSummaryResultsId(int summaryResultsId) {
        this.summaryResultsId = summaryResultsId;
    }

    public int getExpRecId() {
        return expRecId;
    }

    public void setExpRecId(int expRecId) {
        this.expRecId = expRecId;
    }

    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    public void setNumberOfSamples(int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public void setFemaleSamples(int femaleSamples) {
        this.femaleSamples = femaleSamples;
    }

    public int getTissueId() {
        return tissueId;
    }

    public void setTissueId(int tissueId) {
        this.tissueId = tissueId;
    }

    public String getTissueTerm() {
        return tissueTerm;
    }

    public void setTissueTerm(String tissueTerm) {
        this.tissueTerm = tissueTerm;
    }

    public String getParentTissueTerm() {
        return parentTissueTerm;
    }

    public void setParentTissueTerm(String parentTissueTerm) {
        this.parentTissueTerm = parentTissueTerm;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getSignalPresent() {
        return signalPresent;
    }

    public void setSignalPresent(String signalPresent) {
        this.signalPresent = signalPresent;
    }

    public String getSignalDescription() {
        return signalDescription;
    }

    public void setSignalDescription(String signalDescription) {
        this.signalDescription = signalDescription;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getPercentCellsInROIWithSginal() {
        return percentCellsInROIWithSginal;
    }

    public void setPercentCellsInROIWithSginal(String percentCellsInROIWithSginal) {
        this.percentCellsInROIWithSginal = percentCellsInROIWithSginal;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getROICoordinates() {
        return ROICoordinates;
    }

    public void setROICoordinates(String ROICoordinates) {
        this.ROICoordinates = ROICoordinates;
    }

    public String getTissueAccId() {
        return tissueAccId;
    }

    public void setTissueAccId(String tissueAccId) {
        this.tissueAccId = tissueAccId;
    }
}
