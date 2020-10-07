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
        String sql="select * from study s, institution i where s.lab_id=i.institution_id";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q);
    }
}
