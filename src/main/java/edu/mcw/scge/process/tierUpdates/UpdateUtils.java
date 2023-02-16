package edu.mcw.scge.process.tierUpdates;

import edu.mcw.scge.dao.implementation.*;
import edu.mcw.scge.datamodel.*;
import edu.mcw.scge.datamodel.Vector;
import org.apache.poi.ss.formula.functions.T;

import java.util.*;

public class UpdateUtils {
    static final long DAY = 24 * 60 * 60 * 1000;
    StudyDao sdao=new StudyDao();
    ExperimentRecordDao edao= new ExperimentRecordDao();
    GuideDao gdao=new GuideDao();
    VectorDao vectorDao=new VectorDao();
    ProtocolDao protocolDao=new ProtocolDao();
    ModelDao mdao=new ModelDao();
    DeliveryDao deliveryDao=new DeliveryDao();
    EditorDao editorDao=new EditorDao();
    HRDonorDao hrDonorDao=new HRDonorDao();
    TierUpdateDao tierUpdateDao=new TierUpdateDao();
    public void updateOtherExperimentalObjects(StudyTierUpdate update) throws Exception {
        int studyId=update.getStudyId();
        List<ExperimentRecord> records=edao.getExperimentRecordsByStudyId(studyId);
        if(records!=null && records.size()>0)
        for(ExperimentRecord r:records){
            updateGuideTier(r.getExperimentRecordId(),update);
            if(r.getModelId()>0)
            updateModelTier(r.getModelId(), update);
            if(r.getDeliverySystemId()>0)
            updateDeliverySystemTier(r.getDeliverySystemId(), update);
            if(r.getEditorId()>0)
            updateEditorTier(r.getEditorId(), update);

            updateVectorTier(r.getExperimentRecordId(), update);
            updateProtocolTier(r, update);
            updateHrDonorTier(r.getHrdonorId(),update);
        }
    }
    public void updateGuideTier(long expRecId, StudyTierUpdate update) throws Exception {
        List<Guide> guides = gdao.getGuidesByExpRecId(expRecId);
        if(guides!=null && guides.size()>0)
        for(Guide g:guides) {
            if (g.getTier() < update.getTier() || (g.getTier() > update.getTier() && g.getTier() == 2)) {
                tierUpdateDao.updateObjectTier(update, g.getGuide_id());
            }
        }
    }
    public void updateVectorTier(long expRecId, StudyTierUpdate update) throws Exception {
        List<Vector> vectors = vectorDao.getVectorsByExpRecId(expRecId);
        if(vectors!=null && vectors.size()>0)
            for(Vector vector:vectors) {
                if (vector.getTier() < update.getTier() || (vector.getTier() > update.getTier() && vector.getTier() == 2)) {
                    tierUpdateDao.updateObjectTier(update, vector.getVectorId());
                }
            }
    }
    public void updateProtocolTier(ExperimentRecord record, StudyTierUpdate update) throws Exception {
        List<Protocol> protocols = new ArrayList<>();
         try {
             protocols.addAll(protocolDao.getProtocolsForObject(record.getExperimentId()));
         }catch (Exception e){}
         try {
             protocols.addAll(protocolDao.getProtocolsForObject(record.getEditorId()));
         }catch (Exception e){}
         try {
             protocols.addAll(protocolDao.getProtocolsForObject(record.getDeliverySystemId()));

         }catch (Exception e){}
        try {
            protocols.addAll(protocolDao.getProtocolsForObject(record.getModelId()));
        }catch (Exception e){}
        try {
            for (Guide g : gdao.getGuidesByExpRecId(record.getExperimentRecordId()))
                protocols.addAll(protocolDao.getProtocolsForObject(g.getGuide_id()));
        }catch (Exception e){}
        try {
            for (Vector vector : vectorDao.getVectorsByExpRecId(record.getExperimentRecordId()))
                protocols.addAll(protocolDao.getProtocolsForObject(vector.getVectorId()));
        }catch (Exception e){}
        if( protocols.size()>0)
            for(Protocol protocol:protocols) {
                if (protocol.getTier() < update.getTier() || (protocol.getTier() > update.getTier() && protocol.getTier() == 2)) {
                   tierUpdateDao.updateObjectTier(update, protocol.getId());
                }
            }
    }
    public void updateModelTier(long modelId, StudyTierUpdate update) throws Exception {
        Model m=mdao.getModelById(modelId);
        if(m!=null)
        if(m.getTier()<update.getTier() || (m.getTier()>update.getTier() && m.getTier()==2)){
            tierUpdateDao.updateObjectTier(update, modelId);
        }
    }
    public void updateDeliverySystemTier(long dsId, StudyTierUpdate update) throws Exception {
        List<Delivery> dsList=deliveryDao.getDeliverySystemsById(dsId);
        if(dsList!=null && dsList.size()>0) {
            for (Delivery d : dsList) {
                if (d.getTier() < update.getTier() || (d.getTier() > update.getTier() && d.getTier() == 2)) {
                    tierUpdateDao.updateObjectTier(update, dsId);
                }
            }
        }
    }
    public void updateEditorTier(long editorId,StudyTierUpdate update) throws Exception {
        List<Editor> editors=editorDao.getEditorById(editorId);
        if(editors!=null && editors.size()>0) {
            Editor e = editorDao.getEditorById(editorId).get(0);
            if (e.getTier() < update.getTier() || (e.getTier() > update.getTier() && e.getTier() == 2)) {
                tierUpdateDao.updateObjectTier(update, editorId);
            }
        }
    }
    public void updateHrDonorTier(long hrDonorId,StudyTierUpdate update) throws Exception {
        List<HRDonor> donors=hrDonorDao.getHRDonorById(hrDonorId);
        if(donors!=null && donors.size()>0) {
            HRDonor e = hrDonorDao.getHRDonorById(hrDonorId).get(0);
            if (e.getTier() < update.getTier() || (e.getTier() > update.getTier() && e.getTier() == 2)) {
                tierUpdateDao.updateObjectTier(update, hrDonorId);
            }
        }
    }

    public Map<Integer, List<StudyTierUpdate>> getSortedUpdatesMapByStudyId(List<StudyTierUpdate> updates){
        Map<Integer, List<StudyTierUpdate>> updatesMap=new HashMap<>();
        for(StudyTierUpdate u:updates){
     /*      System.out.println("STUDY_ID:"+ u.getStudyId()+"\t"+inLastDay(u) + "\t"+ u.getModifiedTime()+
                   "*********************************************************");*/
            List<StudyTierUpdate> updateList=new ArrayList<>();
            if(updatesMap.get(u.getStudyId())!=null){
                updateList.addAll(updatesMap.get(u.getStudyId()));
            }
            updateList.add(u);
            updatesMap.put(u.getStudyId(),updateList );
        }
        return updatesMap;
    }
    public void loadStudyUpdates(StudyTierUpdate u) throws Exception {
        if(existsStudy(u)){
            if(u.getTier()==2) {
                StudyAssociation a = new StudyAssociation();
                a.setStudyId(u.getStudyId());
                a.setGroupId(u.getAssociatedGroupId());
                a.setTier(u.getTier());
                a.setModifiedBy(String.valueOf(u.getModifiedBy()));
                if (existsAssociation(u)) {
                    a.setAssociationId(sdao.getAssociations(u).get(0));
                    sdao.updateStudyAssociation(a);
                } else {
                    a.setAssociationId(sdao.getNextKey("study_associations_seq"));
                    sdao.insertStudyAssociations(a);

                }
            }


        }
    }
    public boolean existsStudy(StudyTierUpdate u) throws Exception {
        return sdao.getStudyById(u.getStudyId()).size()>0;
    }
    public boolean existsAssociation(StudyTierUpdate u) throws Exception {
        return sdao.existsAssociation(u);
    }
    public boolean inLastDay(StudyTierUpdate u) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(u.getModifiedDate());
        calendar.add(Calendar.HOUR_OF_DAY, u.getModifiedTime().getHours());
        calendar.add(Calendar.MINUTE, u.getModifiedTime().getMinutes());
        calendar.add(Calendar.SECOND, u.getModifiedTime().getSeconds());
        System.out.println("Calendar tme:"+ calendar.getTime().toString());

        System.out.println(calendar.getTimeInMillis() +"\t"+ (System.currentTimeMillis() - DAY )+"\tDAY: "+DAY);
        System.out.println("DIFFERENCE:"+(((System.currentTimeMillis()-calendar.getTimeInMillis())/(1000*60*60*24))));
        return calendar.getTimeInMillis() > System.currentTimeMillis() - DAY;
    }
    public void commitUpdates(int studyId) throws Exception {
        boolean disabled=false;

        List<StudyTierUpdate> updateList= tierUpdateDao.getStudyTierUpdatesByStudyId(studyId);
        for(StudyTierUpdate u:updateList) {

                if(!disabled) {
                    sdao.disableStudyAssociations(u.getStudyId());
                    disabled=true;
                }
                loadStudyUpdates(u);

        }
        if(disabled){
            tierUpdateDao.updateStudyTier(updateList.get(0));
            updateOtherExperimentalObjects(updateList.get(0));
            tierUpdateDao.insertTierLog(studyId);
            tierUpdateDao.delete(studyId);
        }
    }
}
