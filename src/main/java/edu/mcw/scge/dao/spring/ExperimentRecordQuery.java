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
        try {
            e.setExperimentName(rs.getString("experimentName"));
        }catch (Exception exception){}
        e.setExperimentRecordName(rs.getString("name"));
        e.setStudyId(rs.getInt("study_id"));
        e.setGuideId(rs.getInt("guide_id"));
        e.setDeliverySystemId(rs.getInt("ds_id"));
        e.setModelId(rs.getInt("model_id"));
        e.setEditorId(rs.getInt("editor_id"));
        e.setEditorSymbol(rs.getString("symbol"));
        try {
            e.setDeliverySystemType(rs.getString("ds_name"));
        }catch (Exception e2) {
            e.setDeliverySystemType(rs.getString("ds_type"));
        }
        try{
            e.setVector(rs.getString("vector"));
        }catch (Exception e1){

        }
        e.setTissueId(rs.getString("tissue_id"));
        e.setCellType(rs.getString("cell_type"));
        e.setModelName(rs.getString("modelName"));
        e.setGuide(rs.getString("guide"));
        e.setExperimentRecordId(rs.getInt("experiment_record_id"));
        e.setVectorId(rs.getInt("vector_id"));
        return e;





    }
}
