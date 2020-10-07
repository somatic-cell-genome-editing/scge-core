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

        try {
            s.setLabName("institution_name");
        }catch (Exception ignored) {

        }
        return s;
    }
}
