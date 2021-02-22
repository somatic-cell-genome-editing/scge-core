package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Experiment;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimentQuery extends MappingSqlQuery<Experiment> {
    public ExperimentQuery(DataSource ds, String sql){
        super(ds,sql);
    }
    @Override
    protected Experiment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Experiment e= new Experiment();
        e.setExperimentId(rs.getInt("experiment_id"));
        e.setStudyId(rs.getInt("study_id"));
        e.setType(rs.getString("type"));
        e.setName(rs.getString("name"));

        try {
            e.setStudyName(rs.getString("study"));
        }catch (Exception ignored) {

        }

        return e;
    }
}



