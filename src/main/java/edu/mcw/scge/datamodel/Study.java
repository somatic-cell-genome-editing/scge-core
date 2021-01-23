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
    private int submitterId;
    private String submitter;
    private int piId;
    private String pi;
    private String rawData;
    private String reference;
    private List<Integer> associatedGroups;

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
}
