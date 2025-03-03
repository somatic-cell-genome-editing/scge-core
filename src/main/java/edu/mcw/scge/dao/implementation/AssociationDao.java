package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.SCGEIDManagementDao;
import edu.mcw.scge.datamodel.*;
import edu.mcw.scge.datamodel.publications.Publication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssociationDao {
    SCGEIDManagementDao managementDao=new SCGEIDManagementDao();
    EditorDao editorDao=new EditorDao();
    ModelDao modelDao=new ModelDao();
    GuideDao guideDao=new GuideDao();
    DeliveryDao deliveryDao=new DeliveryDao();
    VectorDao vectorDao=new VectorDao();
    ExperimentDao experimentDao=new ExperimentDao();
    ExperimentRecordDao experimentRecordDao=new ExperimentRecordDao();
    StudyDao studyDao=new StudyDao();
    PublicationDAO publicationDAO=new PublicationDAO();
    ProtocolDao protocolDao =new ProtocolDao();
    List<Editor> mapEditorsFromAssociatedObjectsList(List<Long> associatedObjectIds) throws Exception {
        List<Editor> editors=new ArrayList<>();
        for(long id:associatedObjectIds){
            String objectType=managementDao.getObjectTypeById(id);
            if(objectType.equalsIgnoreCase("editor")){
                editors.addAll(editorDao.getEditorById(id));
            }
        }
        return editors;
    }
    List<Model> mapModelsFromAssociatedObjectsList(List<Long> associatedObjectIds) throws Exception {
        List<Model> models=new ArrayList<>();
        for(long id:associatedObjectIds){
            String objectType=managementDao.getObjectTypeById(id);
            if(objectType.equalsIgnoreCase("model")){
                models.add(modelDao.getModelById(id));
            }
        }
        return models;
    }
    List<Guide> mapGuidesFromAssociatedObjectsList(List<Long> associatedObjectIds) throws Exception {
        List<Guide> guides=new ArrayList<>();
        for(long id:associatedObjectIds){
            String objectType=managementDao.getObjectTypeById(id);
            if(objectType.equalsIgnoreCase("guide")){
                guides.addAll(guideDao.getGuideById(id));
            }
        }
        return guides;
    }
    List<Vector> mapVectorsFromAssociatedObjectsList(List<Long> associatedObjectIds) throws Exception {
        List<Vector> vectors=new ArrayList<>();
        for(long id:associatedObjectIds){
            String objectType=managementDao.getObjectTypeById(id);
            if(objectType.equalsIgnoreCase("vector")){
                vectors.addAll(vectorDao.getVectorById(id));
            }
        }
        return vectors;
    }
    List<Delivery> mapDeliverySystemsFromAssociatedObjectsList(List<Long> associatedObjectIds) throws Exception {
        List<Delivery> dsList=new ArrayList<>();
        for(long id:associatedObjectIds){
            String objectType=managementDao.getObjectTypeById(id);
            if(objectType.equalsIgnoreCase("delivery system")){
                dsList.addAll(deliveryDao.getDeliverySystemsById(id));
            }
        }
        return dsList;
    }
    List<Experiment> mapExperimentsFromAssociatedObjectsList(List<Long> associatedObjectIds) throws Exception {
        List<Experiment> experiments=new ArrayList<>();
        for(long id:associatedObjectIds){
            String objectType=managementDao.getObjectTypeById(id);
            if(objectType.equalsIgnoreCase("experiment")){
                experiments.add(experimentDao.getExperiment(id));
            }
        }
        return experiments;
    }
    public List<Publication> mapAssociatedPublications(long protocolId) throws Exception {
        return publicationDAO.getAssociatedPublications(protocolId);
    }
    public List<Publication> mapRelatedPublications(long protocolId) throws Exception {
        return publicationDAO.getRelatedPublications(protocolId);
    }

    public Association getProtocolAssociations(long protocolId) throws Exception {
        Association association =new Association();
        List<Long> associatedObjecIds=protocolDao.getProtocolAssociatedObjectIds(protocolId);
        association.setAssociatedEditors(mapEditorsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedDeliverySystems(mapDeliverySystemsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedGuides(mapGuidesFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedModels(mapModelsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedVectors(mapVectorsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedExperiments(mapExperimentsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedPublications(mapAssociatedPublications(protocolId));
        association.setRelatedPublications(mapRelatedPublications(protocolId));


        return association;
    }
    public Association getPublicationAssociations(int refKey) throws Exception {
        Association association =new Association();
        List<Long> associatedObjecIds=publicationDAO.getPublicationAssoicatedSCGEIds(refKey);
        association.setAssociatedEditors(mapEditorsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedDeliverySystems(mapDeliverySystemsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedGuides(mapGuidesFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedModels(mapModelsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedVectors(mapVectorsFromAssociatedObjectsList(associatedObjecIds));
        association.setAssociatedExperiments(mapExperimentsFromAssociatedObjectsList(associatedObjecIds));

        for(Editor editor: association.getAssociatedEditors()){
            List<Study> objectStudies=studyDao.getStudiesByEditor(editor.getId());
            addStudy(objectStudies, association);

        }
        for(Model object: association.getAssociatedModels()){
            List<Study> objectStudies=studyDao.getStudiesByModel(object.getModelId());
            addStudy(objectStudies, association);
        }
        for(Guide object: association.getAssociatedGuides()){
            List<Study> objectStudies=studyDao.getStudiesByGuide(object.getGuide_id());
            addStudy(objectStudies, association);
        }
        for(Vector object: association.getAssociatedVectors()){
            List<Study> objectStudies=studyDao.getStudiesByVector(object.getVectorId());
            addStudy(objectStudies, association);
        }
        for(Delivery object: association.getAssociatedDeliverySystems()){
            List<Study> objectStudies=studyDao.getStudiesByDeliverySystem(object.getId());
            addStudy(objectStudies, association);
        }
        for(Experiment object: association.getAssociatedExperiments()){
            List<Study> objectStudies=studyDao.getStudyByExperimentId(object.getExperimentId());
            addStudy(objectStudies, association);
        }

        return association;
    }
    void addStudy(List<Study> objectStudies, Association association){
        List<Study> studies= association.getAssociatedStudies();
        if(studies!=null && studies.size()>0){
            for(Study os:objectStudies){
                boolean exists=false;
                for(Study study:studies){
                    if (os.getStudyId() == study.getStudyId()) {
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    studies.add(os);
                }
            }

        }else{
            studies = new ArrayList<>(objectStudies);
        }
        association.setAssociatedStudies(studies);
    }


}
