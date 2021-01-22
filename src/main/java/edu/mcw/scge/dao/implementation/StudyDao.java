package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.StudyAssociationQuery;
import edu.mcw.scge.dao.spring.StudyQuery;
import edu.mcw.scge.datamodel.Study;
import edu.mcw.scge.datamodel.StudyAssociation;

import java.util.Date;
import java.util.List;

public class StudyDao extends AbstractDAO {
    public List<Study> getStudiesByLab(int labId) throws Exception{
        String sql="select * from study where lab_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,labId);
    }
    public Study getStudyByStudyId(int studyId) throws Exception{
        String sql="select * from study where study_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        List<Study>studies= execute(q,studyId);
        if(studies!=null && studies.size()>0){
            return studies.get(0);
        }
        return null;
    }
    public List<StudyAssociation> getStudyAssociations(int studyId) throws Exception {
        String sql="select * from study_associations where study_id=?";
        StudyAssociationQuery q=new StudyAssociationQuery(getDataSource(), sql);
        return execute(q, studyId);

    }
    public List<Study> getStudies() throws Exception{
        String sql="select s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName from study s, institution i, person p, person pi where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q);
    }

    public List<Study> getStudyById(int studyId) throws Exception{
        String sql="select s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName from study s, institution i, person p, person pi where s.study_id=? and s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,studyId);
    }

    public List<Study> getStudiesByEditor(int editorId) throws Exception{
        String sql = "select distinct s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, editor e, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and e.editor_id=? and ex.editor_id=e.editor_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, editorId);
    }

    public List<Study> getStudiesByDeliverySystem(int deliveryId) throws Exception{
        String sql = "select distinct s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, delivery_system d, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and d.ds_id=? and ex.ds_id=d.ds_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, deliveryId);
    }

    public List<Study> getStudiesByVector(int vectorId) throws Exception{
        String sql = "select distinct s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, vector v, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and v.vector_id=? and ex.vector_id=v.vector_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, vectorId);
    }

    public List<Study> getStudiesByModel(int modelId) throws Exception{
        String sql = "select distinct s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, model m, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and m.model_id=? and ex.model_id=m.model_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, modelId);
    }

    public List<Study> getStudiesByGuide(int guideId) throws Exception{
        String sql = "select distinct s.study_id, s.raw_data, s.reference, s.study, s.lab_id, s.tier, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName " +
                "from study s, institution i, person p, person pi, guide g, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id and g.guide_id=? and ex.guide_id=g.guide_id and ex.study_id=s.study_id ";
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


}
