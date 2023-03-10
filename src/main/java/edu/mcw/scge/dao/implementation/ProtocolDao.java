package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.SCGEIDManagementDao;
import edu.mcw.scge.dao.spring.LongListQuery;
import edu.mcw.scge.dao.spring.ProtocolQuery;
import edu.mcw.scge.datamodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProtocolDao extends AbstractDAO {
    SCGEIDManagementDao managementDao=new SCGEIDManagementDao();
    EditorDao editorDao=new EditorDao();
    ModelDao modelDao=new ModelDao();
    GuideDao guideDao=new GuideDao();
    DeliveryDao deliveryDao=new DeliveryDao();
    VectorDao vectorDao=new VectorDao();
    ExperimentDao experimentDao=new ExperimentDao();
    ExperimentRecordDao experimentRecordDao=new ExperimentRecordDao();
    StudyDao studyDao=new StudyDao();

    public Protocol getProtocolById(long protocolId) throws Exception {
        String sql="select * from protocol where protocol_id=?";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        List<Protocol> protocols = execute(q, protocolId);

        if (protocols.size() == 0) {
            throw new Exception ("Protocol " + protocolId + " not found");
        }

        return protocols.get(0);
    }

	public List<Protocol> getProtocols() throws Exception {
        String sql="select * from protocol";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return execute(q);
    }

    public List<Protocol> getProtocolsForObject(long objectId) throws Exception {
        String sql="select * from protocol p, protocol_associations pa where p.protocol_id=pa.protocol_scge_id and pa.object_scge_id=?";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return  execute(q, objectId);

    }

    public List<Protocol> getProtocolsNotAssociatedToObject(long objectId) throws Exception {
        //String sql="select * from protocol p, protocol_associations pa where p.protocol_id=pa.protocol_scge_id and pa.object_scge_id=?";
        String sql = "select * from protocol p where p.protocol_id not in ( ";

         sql += " select p.protocol_id from protocol p, protocol_associations pa where p.protocol_id=pa.protocol_scge_id and pa.object_scge_id=? ";

        sql += ")";



        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return  execute(q, objectId);

    }

    public long insertProtocol(Protocol protocol) throws Exception{

        String sql = "insert into protocol ( title,description,protocol_id,tier," +
                "filename,xref,keywords )" +
                "values (?,?,?,?,?,?,?)";

        long protocolId = this.getNextKeyFromSequenceLong("protocol_seq");

        update(sql, protocol.getTitle(),protocol.getDescription(),protocolId,protocol.getTier(),protocol.getFilename(),
                protocol.getXref(),protocol.getKeywords());

        return protocolId;
    }

    public long insertProtocolAssociation(long protocolId, long objectId) throws Exception{

        String sql = "insert into protocol_associations ( object_scge_id, protocol_scge_id )" +
                "values (?,?)";

        update(sql, objectId, protocolId);

        return protocolId;
    }

    public long deleteProtocolAssociation(long protocolId, long objectId) throws Exception{

        String sql = "delete from protocol_associations where object_scge_id=? and protocol_scge_id=?";

        update(sql, objectId, protocolId);

        return protocolId;
    }


    public void updateProtocol(Protocol protocol) throws Exception{

        String sql = "update protocol set title=?,description=?,tier=?," +
                "filename=?,xref=?,keywords=? where protocol_id = ?";



        update(sql, protocol.getTitle(),protocol.getDescription(),protocol.getTier(),protocol.getFilename(),
                protocol.getXref(),protocol.getKeywords(),protocol.getId());

    }
    public void updateProtocolTier(long protocolId, int updatedTier) throws Exception{

        String sql = "update protocol set tier=? " +
                " where protocol_id = ?";



        update(sql, updatedTier, protocolId);

    }
    public long getProtocolId(Protocol protocol) throws Exception {

        String sql = "select * from protocol where title =? and filename=?";

        List<Protocol> list = ProtocolQuery.execute(this,sql,protocol.getTitle(), protocol.getFilename() );
        return list.isEmpty() ? 0 : list.get(0).getId();
    }
     List<Long> getProtocolAssociatedObjectIds(long protocolId) throws Exception {
        String sql="select object_scge_id from  protocol_associations pa where pa.protocol_scge_id=?";
        LongListQuery q= new LongListQuery(this.getDataSource(), sql);
        return  execute(q, protocolId);
    }
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

    public ProtocolAssociation getProtocolAssociations(long protocolId) throws Exception {
        ProtocolAssociation protocolAssociation=new ProtocolAssociation();
        List<Long> associatedObjecIds=getProtocolAssociatedObjectIds(protocolId);
        protocolAssociation.setAssociatedEditors(mapEditorsFromAssociatedObjectsList(associatedObjecIds));
        protocolAssociation.setAssociatedDeliverySystems(mapDeliverySystemsFromAssociatedObjectsList(associatedObjecIds));
        protocolAssociation.setAssociatedGuides(mapGuidesFromAssociatedObjectsList(associatedObjecIds));
        protocolAssociation.setAssociatedModels(mapModelsFromAssociatedObjectsList(associatedObjecIds));
        protocolAssociation.setAssociatedVectors(mapVectorsFromAssociatedObjectsList(associatedObjecIds));
        protocolAssociation.setAssociatedExperiments(mapExperimentsFromAssociatedObjectsList(associatedObjecIds));

        for(Editor editor:protocolAssociation.getAssociatedEditors()){
            List<Study> objectStudies=studyDao.getStudiesByEditor(editor.getId());
            addStudy(objectStudies, protocolAssociation);

        }
        for(Model object:protocolAssociation.getAssociatedModels()){
            List<Study> objectStudies=studyDao.getStudiesByModel(object.getModelId());
            addStudy(objectStudies, protocolAssociation);
        }
        for(Guide object:protocolAssociation.getAssociatedGuides()){
            List<Study> objectStudies=studyDao.getStudiesByGuide(object.getGuide_id());
            addStudy(objectStudies, protocolAssociation);
        }
        for(Vector object:protocolAssociation.getAssociatedVectors()){
            List<Study> objectStudies=studyDao.getStudiesByVector(object.getVectorId());
            addStudy(objectStudies, protocolAssociation);
        }
        for(Delivery object:protocolAssociation.getAssociatedDeliverySystems()){
            List<Study> objectStudies=studyDao.getStudiesByDeliverySystem(object.getId());
            addStudy(objectStudies, protocolAssociation);
        }
        for(Experiment object:protocolAssociation.getAssociatedExperiments()){
            List<Study> objectStudies=studyDao.getStudyByExperimentId(object.getExperimentId());
            addStudy(objectStudies, protocolAssociation);
        }

        return protocolAssociation;
    }
     void addStudy(List<Study> objectStudies, ProtocolAssociation protocolAssociation){
        List<Study> studies=protocolAssociation.getAssociatedStudies();
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
        protocolAssociation.setAssociatedStudies(studies);
     }
}
