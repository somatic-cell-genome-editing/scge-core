package edu.mcw.scge.datamodel;

/**
 * Created by jdepons on 9/10/2019.
 */
public class Experiment {
    private int id;
    private String name;
    private int editorId;
    private int deliverySystemId;
    private int cellLineId;
    private int reporterAnimalId;
    private int guideId;
    private int targetId;
    private int applicationId;
    private int testId;
    private int resultId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getCellLineId() {
        return cellLineId;
    }

    public void setCellLineId(int cellLineId) {
        this.cellLineId = cellLineId;
    }

    public int getReporterAnimalId() {
        return reporterAnimalId;
    }

    public void setReporterAnimalId(int reporterAnimalId) {
        this.reporterAnimalId = reporterAnimalId;
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

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
}
