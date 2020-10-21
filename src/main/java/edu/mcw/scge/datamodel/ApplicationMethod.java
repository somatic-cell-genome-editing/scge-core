package edu.mcw.scge.datamodel;

public class ApplicationMethod {
    private int applicationId;
    private String applicationType;
    private String siteOfApplication;
    private String  timeCourse;
    private String dosage;
    private String daysPostInjection;

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

    public String getTimeCourse() {
        return timeCourse;
    }

    public void setTimeCourse(String timeCourse) {
        this.timeCourse = timeCourse;
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
}
