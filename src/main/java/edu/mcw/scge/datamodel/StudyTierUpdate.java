package edu.mcw.scge.datamodel;


import java.sql.Date;
import java.sql.Time;

public class StudyTierUpdate {
    private int studyTierUpdateId;
    private int studyId;
    private int groupId;
    private int tier;
    private int memberId;
    private Date modifiedDate;
    private Time modifiedTime;
    private int modifiedBy;
    private String status;
    private String action;

    public int getStudyTierUpdateId() {
        return studyTierUpdateId;
    }

    public void setStudyTierUpdateId(int studyTierUpdateId) {
        this.studyTierUpdateId = studyTierUpdateId;
    }

    public int getStudyId() {
        return studyId;
    }

    public void setStudyId(int studyId) {
        this.studyId = studyId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Time getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Time modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
