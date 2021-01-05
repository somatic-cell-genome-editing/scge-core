package edu.mcw.scge.datamodel;

public class Delivery {
    private int id;
    private String  type;
    private String subtype;
    private String name;
    private String source;
    private String description;
    private String vvGenomeSerotype;
    private String vvCapsidSerotype;
    private String labId;
    private String vvCapsidVariant;
    private String annotatedMap;
    private String titerMethod;
    private String rrid;
    private String npSize;
    private String molTargetingAgent;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getVvGenomeSerotype() {
        return vvGenomeSerotype;
    }

    public void setVvGenomeSerotype(String vvGenomeSerotype) {
        this.vvGenomeSerotype = vvGenomeSerotype;
    }

    public String getVvCapsidSerotype() {
        return vvCapsidSerotype;
    }

    public void setVvCapsidSerotype(String vvCapsidSerotype) {
        this.vvCapsidSerotype = vvCapsidSerotype;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getVvCapsidVariant() {
        return vvCapsidVariant;
    }

    public void setVvCapsidVariant(String vvCapsidVariant) {
        this.vvCapsidVariant = vvCapsidVariant;
    }

    public String getAnnotatedMap() {
        return annotatedMap;
    }

    public void setAnnotatedMap(String annotatedMap) {
        this.annotatedMap = annotatedMap;
    }

    public String getTiterMethod() {
        return titerMethod;
    }

    public void setTiterMethod(String titerMethod) {
        this.titerMethod = titerMethod;
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
}
