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
        e.setResultId(rs.getInt("result_id"));
        e.setExperimentRecordId(rs.getInt("experiment_record_id"));
        e.setAssayDescription(rs.getString("assay_description"));
        e.setNumberOfSamples(rs.getInt("number_of_samples"));
        e.setUnits(rs.getString("units"));
        e.setResultType(rs.getString("result_type"));
        try {
            e.setReplicate(rs.getInt("replicate"));
            e.setResult(rs.getString("result"));
        }catch (Exception ignored) {

        }

        return e;

    }
}
