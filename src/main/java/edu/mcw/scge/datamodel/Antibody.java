package edu.mcw.scge.datamodel;

public class Antibody {
    private int antibodyId;
    private String rrid;
    private String otherId;
    private String description;

    public int getAntibodyId() {
        return antibodyId;
    }

    public void setAntibodyId(int antibodyId) {
        this.antibodyId = antibodyId;
    }

    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
