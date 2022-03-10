package edu.mcw.scge.process.tierUpdates;

import edu.mcw.scge.dao.implementation.*;
import edu.mcw.scge.datamodel.*;
import org.apache.poi.ss.formula.functions.T;

import java.util.*;

public class UpdateUtils {
    static final long DAY = 24 * 60 * 60 * 1000;
    StudyDao sdao=new StudyDao();
    ExperimentRecordDao edao= new ExperimentRecordDao();
    GuideDao gdao=new GuideDao();
    ModelDao mdao=new ModelDao();
    DeliveryDao deliveryDao=new DeliveryDao();
    EditorDao editorDao=new EditorDao();
    TierUpdateDao tierUpdateDao=new TierUpdateDao();
    public void updateOtherExperimentalObjects(StudyTierUpdate update) throws Exception {
        int studyId=update.getStudyId();
        List<ExperimentRecord> records=edao.getExperimentRecordsByStudyId(studyId);
        if(records!=null && records.size()>0)
        for(ExperimentRecord r:records){
            updateGuideTier(r.getExperimentRecordId(), update.getTier());
            if(r.getModelId()>0)
            updateModelTier(r.getModelId(), update.getTier());
            if(r.getDeliverySystemId()>0)
            updateDeliverySystemTier(r.getDeliverySystemId(), update.getTier());
            if(r.getEditorId()>0)
            updateEditorTier(r.getEditorId(), update.getTier());

        }
    }
    public void updateGuideTier(long expRecId, int updatedTier) throws Exception {
        List<Guide> guides = gdao.getGuidesByExpRecId(expRecId);
        if(guides!=null && guides.size()>0)
        for(Guide g:guides) {
            //Guide g = gdao.getGuideById(guideId).get(0);
            if (g.getTier() < updatedTier || (g.getTier() > updatedTier && g.getTier() == 2)) {
                gdao.updateGuideTier(updatedTier, g.getGuide_id());
            }
        }
    }
    public void updateModelTier(long modelId, int updatedTier) throws Exception {
        Model m=mdao.getModelById(modelId);
        if(m!=null)
        if(m.getTier()<updatedTier || (m.getTier()>updatedTier && m.getTier()==2)){
            mdao.updateModelTier(updatedTier, modelId);
        }
    }
    public void updateDeliverySystemTier(long dsId, int updatedTier) throws Exception {
        List<Delivery> dsList=deliveryDao.getDeliverySystemsById(dsId);
        if(dsList!=null && dsList.size()>0) {
            for (Delivery d : dsList) {
                if (d.getTier() < updatedTier || (d.getTier() > updatedTier && d.getTier() == 2)) {
                    deliveryDao.updateDeliveryTier(updatedTier, dsId);
                }
            }
        }
    }
    public void updateEditorTier(long editorId, int updatedTIer) throws Exception {
        List<Editor> editors=editorDao.getEditorById(editorId);
        if(editors!=null && editors.size()>0) {
            Editor e = editorDao.getEditorById(editorId).get(0);
            if (e.getTier() < updatedTIer || (e.getTier() > updatedTIer && e.getTier() == 2)) {
                editorDao.updateEditorTier(updatedTIer, editorId);
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
            sdao.updateStudyTier(updateList.get(0));
            updateOtherExperimentalObjects(updateList.get(0));
            tierUpdateDao.insertTierLog(studyId);
            tierUpdateDao.delete(studyId);
        }
    }
}
