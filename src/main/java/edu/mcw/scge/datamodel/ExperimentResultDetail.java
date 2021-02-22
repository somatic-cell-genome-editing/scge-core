package edu.mcw.scge.datamodel;

public class ExperimentResultDetail {

    private int resultId;
    private int replicate;
    private String result;

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
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
}
