package edu.mcw.scge.dao.spring;

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
        ExperimentRecord record=new ExperimentRecord();
        record.setExperimentId(rs.getInt("EXPERIMENT_ID"));
      //  record.setExperimentRecId(rs.getInt("EXPERIMENT_RECORD_ID"));
        record.setEditorType(rs.getString("subtype"));
        record.setGuide(rs.getString("guide"));
        record.setModel(rs.getString("name"));
       // record.setDeliveryType(rs.getString("delivery_system_subtype"));
        record.setGuideDetectionMethod(rs.getString("detection_method"));
        record.setSpecificity(rs.getDouble("specificity_ratio"));
        record.setTargetLocus(rs.getString("locus_symbol"));
        record.setTargetLocusSymbol(rs.getString("locus_id"));

        return record;
    }
}
