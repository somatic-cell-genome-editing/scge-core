package edu.mcw.scge.datamodel;

import java.util.Date;
import java.util.List;

public class Study {

    private int studyId;
    private String study;
    private int labId;
    private String labName;
    private int tier;
    private Date submissionDate;
    private Date lastModifiedDate;
    private int submitterId;
    private String submitter;
    private int piId;
    private String pi;
    private String piFirstName;
    private String piLastName;

    private String rawData;
    private String reference;
    private List<Integer> associatedGroups;
    private int groupId;
    private int deliveryPiId;
    private String deliveryPiName;

    private int isValidationStudy;
    private Study validationStudy;
    private List<Study> validatedStudies;

    public int getIsValidationStudy() {
        return isValidationStudy;
    }

    public void setIsValidationStudy(int isValidationStudy) {
        this.isValidationStudy = isValidationStudy;
    }

    public Study getValidationStudy() {
        return validationStudy;
    }

    public void setValidationStudy(Study validationStudy) {
        this.validationStudy = validationStudy;
    }

    public List<Study> getValidatedStudies() {
        return validatedStudies;
    }

    public void setValidatedStudies(List<Study> validatedStudies) {
        this.validatedStudies = validatedStudies;
    }

    public int getDeliveryPiId() {
        return deliveryPiId;
    }

    public void setDeliveryPiId(int deliveryPiId) {
        this.deliveryPiId = deliveryPiId;
    }

    public String getDeliveryPiName() {
        return deliveryPiName;
    }

    public void setDeliveryPiName(String deliveryPiName) {
        this.deliveryPiName = deliveryPiName;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getStudyId() {
        return studyId;
    }

    public void setStudyId(int studyId) {
        this.studyId = studyId;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }


    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public int getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(int submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public int getPiId() {
        return piId;
    }

    public void setPiId(int piId) {
        this.piId = piId;
    }

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Integer> getAssociatedGroups() {
        return associatedGroups;
    }

    public void setAssociatedGroups(List<Integer> associatedGroups) {
        this.associatedGroups = associatedGroups;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getPiFirstName() {
        return piFirstName;
    }

    public void setPiFirstName(String piFirstName) {
        this.piFirstName = piFirstName;
    }

    public String getPiLastName() {
        return piLastName;
    }

    public void setPiLastName(String piLastName) {
        this.piLastName = piLastName;
    }
}
