package edu.mcw.scge.datamodel;

public class Delivery {
    private long id;
    private String  type;
    private String subtype;
    private String name;
    private String source;
    private String description;
    private String labId;
    private String annotatedMap;
    private String rrid;
    private String npSize;
    private String molTargetingAgent;
    private int tier;
    private String sequence;
    private String zetaPotential;
    private String npPolydispersityIndex;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getAnnotatedMap() {
        return annotatedMap;
    }

    public void setAnnotatedMap(String annotatedMap) {
        this.annotatedMap = annotatedMap;
    }

    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid;
    }

    public String getNpSize() {
        return npSize;
    }

    public void setNpSize(String npSize) {
        this.npSize = npSize;
    }

    public String getMolTargetingAgent() {
        return molTargetingAgent;
    }

    public void setMolTargetingAgent(String molTargetingAgent) {
        this.molTargetingAgent = molTargetingAgent;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getZetaPotential() {
        return zetaPotential;
    }

    public void setZetaPotential(String zetaPotential) {
        this.zetaPotential = zetaPotential;
    }

    public String getNpPolydispersityIndex() {
        return npPolydispersityIndex;
    }

    public void setNpPolydispersityIndex(String npPolydispersityIndex) {
        this.npPolydispersityIndex = npPolydispersityIndex;
    }
}
