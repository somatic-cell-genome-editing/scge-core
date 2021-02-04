package edu.mcw.scge.datamodel;

public class Model {

   private int modelId;
   private String type;
    private String name;
    private String  organism;
     private String sex;
     private String rrid;
     private String source;
     private String transgene;
     private String subtype;
     private String annotatedMap;
     private String transgeneDescription;
     private String transgeneReporter;
    private String parentalOrigin;
    private String description;
    private String strainCode;
    private String strainAlias;


    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTransgene() {
        return transgene;
    }

    public void setTransgene(String transgene) {
        this.transgene = transgene;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getAnnotatedMap() {
        return annotatedMap;
    }

    public void setAnnotatedMap(String annotatedMap) {
        this.annotatedMap = annotatedMap;
    }

    public String getTransgeneDescription() {
        return transgeneDescription;
    }

    public void setTransgeneDescription(String transgeneDescription) {
        this.transgeneDescription = transgeneDescription;
    }

    public String getTransgeneReporter() {
        return transgeneReporter;
    }

    public void setTransgeneReporter(String transgeneReporter) {
        this.transgeneReporter = transgeneReporter;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getParentalOrigin() {
        return parentalOrigin;
    }

    public void setParentalOrigin(String parentalOrigin) {
        this.parentalOrigin = parentalOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrainCode() {
        return strainCode;
    }

    public void setStrainCode(String strainCode) {
        this.strainCode = strainCode;
    }

    public String getStrainAlias() {
        return strainAlias;
    }

    public void setStrainAlias(String strainAlias) {
        this.strainAlias = strainAlias;
    }
}
