package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Study;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyQuery extends MappingSqlQuery {
    public StudyQuery(DataSource ds, String sql){
        super(ds,sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Study s=new Study();
        s.setLabId(rs.getInt("lab_id"));
        s.setStudyId(rs.getInt("study_id"));
        s.setStudy(rs.getString("study"));
        s.setTier(rs.getInt("tier"));
        s.setSubmissionDate(rs.getDate("submission_date"));
        s.setLastModifiedDate(rs.getDate("last_modified_date"));

        s.setSubmitterId(rs.getInt("submitter_id"));
        s.setRawData(rs.getString("raw_data"));
        s.setReference(rs.getString("reference"));
        s.setGroupId(rs.getInt("group_id"));
        s.setPiId(rs.getInt("pi_id"));
        try{
            s.setPiId(rs.getInt("piId"));
        }catch (Exception e){}
        try {
            s.setLabName(rs.getString("institution_name"));

        }catch (Exception e){}
        try{
            s.setSubmitter(rs.getString("submitterName"));
        }catch (Exception e){}
        try {
            s.setPi(rs.getString("piName"));
        }catch (Exception e){}

        return s;
    }
}
