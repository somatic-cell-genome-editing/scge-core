package edu.mcw.scge.datamodel;

import java.util.List;

public class Grant {
   private int grantId;
   private int groupId;
   private String grantTitle;
   private String  grantTitleLc;
   private String grantNumber;
   private String grantInitiative;
   private String description;
   private String currentGrantNumber;
   private List<String> formerGrantNumbers;

    public String getCurrentGrantNumber() {
        return currentGrantNumber;
    }

    public void setCurrentGrantNumber(String currentGrantNumber) {
        this.currentGrantNumber = currentGrantNumber;
    }

    public List<String> getFormerGrantNumbers() {
        return formerGrantNumbers;
    }

    public void setFormerGrantNumbers(List<String> formerGrantNumbers) {
        this.formerGrantNumbers = formerGrantNumbers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrantId() {
        return grantId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    public String getGrantTitle() {
        return grantTitle;
    }

    public void setGrantTitle(String grantTitle) {
        this.grantTitle = grantTitle;
    }

    public String getGrantTitleLc() {
        return grantTitleLc;
    }

    public void setGrantTitleLc(String grantTitleLc) {
        this.grantTitleLc = grantTitleLc;
    }

    public String getGrantNumber() {
        return grantNumber;
    }

    public void setGrantNumber(String grantNumber) {
        this.grantNumber = grantNumber;
    }

    public String getGrantInitiative() {
        return grantInitiative;
    }

    public void setGrantInitiative(String grantInitiative) {
        this.grantInitiative = grantInitiative;
    }
}
