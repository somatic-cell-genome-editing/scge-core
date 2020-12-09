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
        e.setExperimentName(rs.getString("experiment_name"));
        e.setStudyId(rs.getInt("study_id"));
        e.setGuideId(rs.getInt("guide_id"));
        e.setDeliverySystemId(rs.getInt("ds_id"));
        e.setModelId(rs.getInt("model_id"));
        e.setEditorId(rs.getInt("editor_id"));
        e.setEditorSymbol(rs.getString("symbol"));
        e.setDeliverySystemType(rs.getString("ds_type"));
        e.setModelName(rs.getString("name"));
        e.setGuide(rs.getString("guide"));
        return e;
    }
}



