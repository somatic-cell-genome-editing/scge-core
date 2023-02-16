package edu.mcw.scge.datamodel;

import java.sql.Date;

public class Tier {
    private long scgeId;
    private int tier;
    private Date lastModifiedDate;
    private String lastModifiedBy;


    public long getScgeId() {
        return scgeId;
    }

    public void setScgeId(long scgeId) {
        this.scgeId = scgeId;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
