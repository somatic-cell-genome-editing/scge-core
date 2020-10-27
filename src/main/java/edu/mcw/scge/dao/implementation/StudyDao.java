package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.StudyQuery;
import edu.mcw.scge.datamodel.Study;

import java.util.List;

public class StudyDao extends AbstractDAO {
    public List<Study> getStudiesByLab(int labId) throws Exception{
        String sql="select * from study where lab_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,labId);
    }

    public List<Study> getStudies() throws Exception{
        String sql="select s.study_id, s.study, s.lab_id, s.tier, s.type, s.submission_date, s.submitter_id as submitterId, i.institution_name, p.name as submitterName, pi.person_id as piId, pi.name as piName from study s, institution i, person p, person pi where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q);
    }
}
