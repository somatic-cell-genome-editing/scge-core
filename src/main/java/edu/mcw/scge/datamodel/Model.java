package edu.mcw.scge.datamodel;

public class Model {

   private int modelId;
   private String type;
    private String name;
    private String  organism;
    private String age;
     private String sex;
    private String genotype;
     private String stockNumber;
    private String shortName;
     private String ageRange;
     private String rrid;
     private String source;
     private String transgene;
     private String subtype;
     private String annotatedMap;
     private String transgeneDescription;
     private String transgeneReporter;
     private String reporterDbIds;

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

    public String getReporterDbIds() {
        return reporterDbIds;
    }

    public void setReporterDbIds(String reporterDbIds) {
        this.reporterDbIds = reporterDbIds;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
}
