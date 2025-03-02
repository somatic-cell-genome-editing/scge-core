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
        e.setExperimentId(rs.getLong("experiment_id"));
        e.setStudyId(rs.getInt("study_id"));
        e.setType(rs.getString("type"));
        e.setName(rs.getString("name"));
        e.setDescription(rs.getString("description"));

        try {
            e.setStudyName(rs.getString("study"));
        }catch (Exception ignored) {

        }
        try{
            e.setLastModifiedDate(rs.getDate("last_modified_date"));
        }catch (Exception ignored){

        }
        try{
            e.setTier(rs.getInt("tier"));
        }catch (Exception ignored){}

        return e;
    }
}



