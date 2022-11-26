package edu.mcw.scge.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plot {
    private String xaxisLabel;
    private String yaxisLabel;
    private String title;
    private List<String> tickLabels;
    private Map<String, List<Double>> plotData;
    private HashMap<Integer, List<Integer>> replicateResult;
    private Experiment experiment;
    private List<Object> comparableObjects;

    public String getXaxisLabel() {
        return xaxisLabel;
    }

    public void setXaxisLabel(String xaxisLabel) {
        this.xaxisLabel = xaxisLabel;
    }

    public String getYaxisLabel() {
        return yaxisLabel;
    }

    public void setYaxisLabel(String yaxisLabel) {
        this.yaxisLabel = yaxisLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTickLabels() {
        return tickLabels;
    }

    public void setTickLabels(List<String> tickLabels) {
        this.tickLabels = tickLabels;
    }

    public Map<String, List<Double>> getPlotData() {
        return plotData;
    }

    public void setPlotData(Map<String, List<Double>> plotData) {
        this.plotData = plotData;
    }

    public HashMap<Integer, List<Integer>> getReplicateResult() {
        return replicateResult;
    }

    public void setReplicateResult(HashMap<Integer, List<Integer>> replicateResult) {
        this.replicateResult = replicateResult;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public List<Object> getComparableObjects() {
        return comparableObjects;
    }

    public void setComparableObjects(List<Object> comparableObjects) {
        this.comparableObjects = comparableObjects;
    }
}
