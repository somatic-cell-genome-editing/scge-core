package edu.mcw.scge.datamodel;

public class Vector {
    private int vectorId;
    private String name;
    private String type;
    private String subtype;
    private String genomeSerotype;
    private String capsidSerotype;
    private String description;
    private String capsidVariant;
    private String source;
    private String labId;
    private String annotatedMap;
    private String titerMethod;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAnnotatedMap() {
        return annotatedMap;
    }

    public void setAnnotatedMap(String annotatedMap) {
        this.annotatedMap = annotatedMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVectorId() {
        return vectorId;
    }

    public void setVectorId(int vectorId) {
        this.vectorId = vectorId;
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

    public String getGenomeSerotype() {
        return genomeSerotype;
    }

    public void setGenomeSerotype(String genomeSerotype) {
        this.genomeSerotype = genomeSerotype;
    }

    public String getCapsidSerotype() {
        return capsidSerotype;
    }

    public void setCapsidSerotype(String capsidSerotype) {
        this.capsidSerotype = capsidSerotype;
    }

    public String getCapsidVariant() {
        return capsidVariant;
    }

    public void setCapsidVariant(String capsidVariant) {
        this.capsidVariant = capsidVariant;
    }

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getTiterMethod() {
        return titerMethod;
    }

    public void setTiterMethod(String titerMethod) {
        this.titerMethod = titerMethod;
    }
}
