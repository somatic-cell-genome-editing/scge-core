package edu.mcw.scge.datamodel;

import edu.mcw.scge.datamodel.publications.Publication;

import java.util.List;

public class ProtocolAssociation extends Protocol {
    private List<Editor> associatedEditors;
    private List<Guide> associatedGuides;
    private List<Delivery> associatedDeliverySystems;
    private List<Vector> associatedVectors;
    private List<Model> associatedModels;
    private List<Study> associatedStudies;
    private List<Experiment> associatedExperiments;
    private List<Publication> associatedPublications;
    private List<Publication> relatedPublications;

    public List<Publication> getRelatedPublications() {
        return relatedPublications;
    }

    public void setRelatedPublications(List<Publication> relatedPublications) {
        this.relatedPublications = relatedPublications;
    }

    public List<Publication> getAssociatedPublications() {
        return associatedPublications;
    }

    public void setAssociatedPublications(List<Publication> associatedPublications) {
        this.associatedPublications = associatedPublications;
    }

    public List<Experiment> getAssociatedExperiments() {
        return associatedExperiments;
    }

    public void setAssociatedExperiments(List<Experiment> associatedExperiments) {
        this.associatedExperiments = associatedExperiments;
    }

    public List<Editor> getAssociatedEditors() {
        return associatedEditors;
    }

    public void setAssociatedEditors(List<Editor> associatedEditors) {
        this.associatedEditors = associatedEditors;
    }

    public List<Guide> getAssociatedGuides() {
        return associatedGuides;
    }

    public void setAssociatedGuides(List<Guide> associatedGuides) {
        this.associatedGuides = associatedGuides;
    }

    public List<Delivery> getAssociatedDeliverySystems() {
        return associatedDeliverySystems;
    }

    public void setAssociatedDeliverySystems(List<Delivery> associatedDeliverySystems) {
        this.associatedDeliverySystems = associatedDeliverySystems;
    }

    public List<Vector> getAssociatedVectors() {
        return associatedVectors;
    }

    public void setAssociatedVectors(List<Vector> associatedVectors) {
        this.associatedVectors = associatedVectors;
    }

    public List<Model> getAssociatedModels() {
        return associatedModels;
    }

    public void setAssociatedModels(List<Model> associatedModels) {
        this.associatedModels = associatedModels;
    }

    public List<Study> getAssociatedStudies() {
        return associatedStudies;
    }

    public void setAssociatedStudies(List<Study> associatedStudies) {
        this.associatedStudies = associatedStudies;
    }
}
