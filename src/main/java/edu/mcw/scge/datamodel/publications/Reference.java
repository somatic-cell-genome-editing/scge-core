package edu.mcw.scge.datamodel.publications;

import java.util.Date;
import java.util.List;

public class Reference {
    private int key;
    private String title;
    private String editors;
    private String publication;
    private String volume;
    private String issue;
    private String pages;
    private String pubStatus;
    private Date pubDate;
    private String notes;
    private String referenceType;
    private String citation;
    private String refAbstract;
    private String publisher;
    private String publisherCity;
    private String urlWebReference;
    private int speciesTypeKey;
    private String doi;
    private String pubmedId;
    private List<String> meshTerms;

    private long associatedSCGEId;

    public long getAssociatedSCGEId() {
        return associatedSCGEId;
    }

    public void setAssociatedSCGEId(long associatedSCGEId) {
        this.associatedSCGEId = associatedSCGEId;
    }

    public List<String> getMeshTerms() {
        return meshTerms;
    }

    public void setMeshTerms(List<String> meshTerms) {
        this.meshTerms = meshTerms;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditors() {
        return editors;
    }

    public void setEditors(String editors) {
        this.editors = editors;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(String pubStatus) {
        this.pubStatus = pubStatus;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getRefAbstract() {
        return refAbstract;
    }

    public void setRefAbstract(String refAbstract) {
        this.refAbstract = refAbstract;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }

    public String getUrlWebReference() {
        return urlWebReference;
    }

    public void setUrlWebReference(String urlWebReference) {
        this.urlWebReference = urlWebReference;
    }

    public int getSpeciesTypeKey() {
        return speciesTypeKey;
    }

    public void setSpeciesTypeKey(int speciesTypeKey) {
        this.speciesTypeKey = speciesTypeKey;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(String pubmedId) {
        this.pubmedId = pubmedId;
    }



}
