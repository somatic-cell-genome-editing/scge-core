package edu.mcw.scge.datamodel;

public class ApplicationMethod {
    private int applicationId;
    private String applicationType;
    private String siteOfApplication;
    private String dosage;
    private String daysPostInjection;
    private String injectionRate;
    private String injectionVolume;
    private String injectionFrequency;
    private String editorFormat;
    private String antidoteId;
    private String antidoteDescription;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getSiteOfApplication() {
        return siteOfApplication;
    }

    public void setSiteOfApplication(String siteOfApplication) {
        this.siteOfApplication = siteOfApplication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDaysPostInjection() {
        return daysPostInjection;
    }

    public void setDaysPostInjection(String daysPostInjection) {
        this.daysPostInjection = daysPostInjection;
    }

    public String getInjectionRate() {
        return injectionRate;
    }

    public void setInjectionRate(String injectionRate) {
        this.injectionRate = injectionRate;
    }

    public String getInjectionVolume() {
        return injectionVolume;
    }

    public void setInjectionVolume(String injectionVolume) {
        this.injectionVolume = injectionVolume;
    }

    public String getInjectionFrequency() {
        return injectionFrequency;
    }

    public void setInjectionFrequency(String injectionFrequency) {
        this.injectionFrequency = injectionFrequency;
    }

    public String getEditorFormat() {
        return editorFormat;
    }

    public void setEditorFormat(String editorFormat) {
        this.editorFormat = editorFormat;
    }

    public String getAntidoteId() {
        return antidoteId;
    }

    public void setAntidoteId(String antidoteId) {
        this.antidoteId = antidoteId;
    }

    public String getAntidoteDescription() {
        return antidoteDescription;
    }

    public void setAntidoteDescription(String antidoteDescription) {
        this.antidoteDescription = antidoteDescription;
    }

}
