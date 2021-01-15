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
        s.setSubmitterId(rs.getInt("submitterId"));
        s.setPiId(rs.getInt("piId"));
        s.setLabName(rs.getString("institution_name"));
        s.setSubmitter(rs.getString("submitterName"));
        s.setPi(rs.getString("piName"));
        s.setRawData(rs.getString("raw_data"));
        s.setReference(rs.getString("reference"));

        return s;
    }
}
