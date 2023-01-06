package edu.mcw.scge.datamodel;

import java.util.List;

public class ExperimentRecord {
    private long experimentRecordId;
    private long experimentId;
    private String experimentRecordName;
    private String experimentName;
    private String experimentType;
    private int studyId;
    private long editorId;
    private long deliverySystemId;
    private long hrdonorId;


    //  private int vectorId;
    private long modelId;
    //private int guideId;
    private int applicationMethodId;
    private String editorSymbol;
    private String deliverySystemType;
    private String deliverySystemName;
   // private String guide;
    private String modelName;
    private String modelDisplayName;
    private String studyName;
    private String age;
    private String genotype;
    private String sex;
    private String tissueId;
    private String cellType;
    private String tissueTerm;
    private String organSystemID;
    private String cellTypeTerm ;
  //  private String vector;
    private String dosage;
    private String injectionFrequency;
    private String condition;
    private String hrdonorName;

    private int isTargetTissue;

    private List<Guide> guides;
    private List<Vector> vectors;
    private List<HRDonor> hrDonors;
    private List<ExperimentResultDetail> resultDetails;

    public List<Guide> getGuides() {
        return guides;
    }

    public void setGuides(List<Guide> guides) {
        this.guides = guides;
    }

    public List<Vector> getVectors() {
        return vectors;
    }

    public void setVectors(List<Vector> vectors) {
        this.vectors = vectors;
    }

    public List<HRDonor> getHrDonors() {
        return hrDonors;
    }

    public void setHrDonors(List<HRDonor> hrDonors) {
        this.hrDonors = hrDonors;
    }

    public List<ExperimentResultDetail> getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(List<ExperimentResultDetail> resultDetails) {
        this.resultDetails = resultDetails;
    }

    public String getOrganSystemID() {
        return organSystemID;
    }

    public void setOrganSystemID(String organSystemID) {
        this.organSystemID = organSystemID;
    }

    public String getModelDisplayName() {
        return modelDisplayName;
    }

    public void setModelDisplayName(String modelDisplayName) {
        this.modelDisplayName = modelDisplayName;
    }

    public int getIsTargetTissue() {
        return isTargetTissue;
    }

    public void setIsTargetTissue(int isTargetTissue) {
        this.isTargetTissue = isTargetTissue;
    }

    public String getExperimentRecordName() {
        return experimentRecordName;
    }

    public void setExperimentRecordName(String experimentRecordName) {
        this.experimentRecordName = experimentRecordName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getInjectionFrequency() {
        return injectionFrequency;
    }

    public void setInjectionFrequency(String injectionFrequency) {
        this.injectionFrequency = injectionFrequency;
    }

    public String getDeliverySystemName() {
        return deliverySystemName;
    }

    public void setDeliverySystemName(String deliverySystemName) {
        this.deliverySystemName = deliverySystemName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getCellTypeTerm() {
        return cellTypeTerm;
    }

    public void setCellTypeTerm(String cellTypeTerm) {
        if (cellTypeTerm == null || cellTypeTerm.equals("")) {
            cellTypeTerm = "unspecified";
        }
        this.cellTypeTerm = cellTypeTerm;
    }

    public String getTissueTerm() {
        return tissueTerm;
    }

    public void setTissueTerm(String tissueTerm) {
        this.tissueTerm = tissueTerm;
    }

    public long getExperimentRecordId() {
        return experimentRecordId;
    }

    public void setExperimentRecordId(long experimentRecordId) {
        this.experimentRecordId = experimentRecordId;
    }

    public int getStudyId() {
        return studyId;
    }

    public void setStudyId(int studyId) {
        this.studyId = studyId;
    }

    public long getDeliverySystemId() {
        return deliverySystemId;
    }

    public void setDeliverySystemId(long deliverySystemId) {
        this.deliverySystemId = deliverySystemId;
    }

 /*   public int getVectorId() {
        return vectorId;
    }

    public void setVectorId(int vectorId) {
        this.vectorId = vectorId;
    }
*/
    public String getEditorSymbol() {
        return editorSymbol;
    }

    public void setEditorSymbol(String editorSymbol) {
        this.editorSymbol = editorSymbol;
    }

    public String getDeliverySystemType() {
        return deliverySystemType;
    }

    public void setDeliverySystemType(String deliverySystemType) {
        this.deliverySystemType = deliverySystemType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public long getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(long experimentId) {
        this.experimentId = experimentId;
    }

   /* public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

   */ public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public long getEditorId() {
        return editorId;
    }

    public void setEditorId(long editorId) {
        this.editorId = editorId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

  /*  public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }
*/

    public int getApplicationMethodId() {
        return applicationMethodId;
    }

    public void setApplicationMethodId(int applicationMethodId) {
        this.applicationMethodId = applicationMethodId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(String experimentType) {
        this.experimentType = experimentType;
    }

    public String getTissueId() {
        return tissueId;
    }

    public void setTissueId(String tissueId) {
        this.tissueId = tissueId;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

 /*   public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }
    */

    public long getHrdonorId() {
        return hrdonorId;
    }

    public void setHrdonorId(long hrdonorId) {
        this.hrdonorId = hrdonorId;
    }

    public String getHrdonorName() {
        return hrdonorName;
    }

    public void setHrdonorName(String hrdonorName) {
        this.hrdonorName = hrdonorName;
    }
}
