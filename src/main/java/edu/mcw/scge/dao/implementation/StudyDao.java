package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.StudyQuery;
import edu.mcw.scge.datamodel.Study;

import java.util.Date;
import java.util.List;

public class StudyDao extends AbstractDAO {
    public List<Study> getStudiesByLab(int labId) throws Exception{
        String sql="select * from study where lab_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,labId);
    }

    public List<Study> getStudies() throws Exception{
        String sql="select s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.type, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName from study s, institution i, person p, person pi where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q);
    }




















    public List<Study> getStudiesByEditor(int editorId) throws Exception{
        String sql = "select s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.type, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, editor e, experiment ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and e.editor_id=? and ex.editor_id=e.editor_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, editorId);
    }

    public List<Study> getStudiesByDeliverySystem(int deliveryId) throws Exception{
        String sql = "select s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.type, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, delivery_system d, experiment ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and d.ds_id=? and ex.delivery_system_id=d.ds_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, deliveryId);
    }

    public List<Study> getStudiesByModel(int modelId) throws Exception{
        String sql = "select s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.type, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, model m, experiment ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and m.model_id=? and ex.model_id=m.model_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, modelId);
    }

    public List<Study> getStudiesByGuide(int guideId) throws Exception{
        String sql = "select s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.type, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, guide g, experiment ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and gu.guide_id=? and ex.editor_id=g.guide_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }


    /**
     * inserts into temporary table study_tier_updates until the change is approved by PI
     *
     */
    public void insertStudyTier(int studyId, int tier, int groupId, int sequenceKey, String action,
                                String status, int modifiedByPersonId) throws Exception {
        String sql="insert into study_tier_updates(" +
                "study_tier_update_id," +
                "study_id," +
                "tier," +
                "modified_date," +
                "modified_by," +
                "group_id," +
                "status," +
                "action )" +
                " values(?,?,?,?,?,?,?,?)";
        update(sql,
               sequenceKey, studyId, tier,new Date(),modifiedByPersonId, groupId,status,action
        );
    }

    /**
     * pulls study from temporary table study_tier_updates
     */
    public void getStudyTier(int studyId, int groupId){
        String sql="select * from study_tier_updates where study_id=? and group_id=?";
    }
}
