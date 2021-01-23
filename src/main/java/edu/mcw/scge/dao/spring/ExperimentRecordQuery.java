package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Experiment;
import edu.mcw.scge.datamodel.ExperimentRecord;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimentRecordQuery extends MappingSqlQuery<ExperimentRecord> {
    public ExperimentRecordQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected ExperimentRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExperimentRecord e= new ExperimentRecord();
        e.setExperimentId(rs.getInt("experiment_id"));
        e.setExperimentName(rs.getString("name"));
        e.setExperimentType(rs.getString("type"));
        e.setStudyId(rs.getInt("study_id"));
        e.setGuideId(rs.getInt("guide_id"));
        e.setDeliverySystemId(rs.getInt("ds_id"));
        e.setModelId(rs.getInt("model_id"));
        e.setEditorId(rs.getInt("editor_id"));
        e.setEditorSymbol(rs.getString("symbol"));
        e.setDeliverySystemType(rs.getString("ds_type"));
        e.setModelName(rs.getString("modelName"));
        e.setGuide(rs.getString("guide"));
        e.setExperimentRecordId(rs.getInt("experiment_record_id"));

        return e;





    }
}
