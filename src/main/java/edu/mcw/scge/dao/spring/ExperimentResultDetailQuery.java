package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.ExperimentResultDetail;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimentResultDetailQuery extends MappingSqlQuery<ExperimentResultDetail> {
    public ExperimentResultDetailQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected ExperimentResultDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

        ExperimentResultDetail e= new ExperimentResultDetail();
        e.setResultId(rs.getLong("result_id"));
        e.setExperimentRecordId(rs.getLong("experiment_record_id"));
        try {
            e.setExperimentConditionName(rs.getString("name"));
        }catch (Exception e1){}
        e.setAssayDescription(rs.getString("assay_description"));
        e.setNumberOfSamples(rs.getInt("number_of_samples"));
        e.setUnits(rs.getString("units"));
        e.setResultType(rs.getString("result_type"));
        e.setEditType(rs.getString("edit_type"));
        try {
            e.setReplicate(rs.getInt("replicate"));
            e.setResult(rs.getString("result"));
        }catch (Exception ignored) {

        }

        return e;

    }
}
