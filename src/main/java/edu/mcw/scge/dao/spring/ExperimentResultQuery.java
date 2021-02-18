package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.ExperimentResult;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimentResultQuery extends MappingSqlQuery<ExperimentResult> {
    public ExperimentResultQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected ExperimentResult mapRow(ResultSet rs, int rowNum) throws SQLException {

        ExperimentResult e= new ExperimentResult();

        e.setResultId(rs.getInt("result_id"));
        e.setTissueId(rs.getString("tissue_id"));
        e.setCellType(rs.getString("cell_type"));
        e.setExperimentRecordId(rs.getInt("experiment_record_id"));
        e.setAssayDescription(rs.getString("assay_description"));
        e.setNumberOfSamples(rs.getInt("number_of_samples"));
        e.setUnits(rs.getString("units"));
        e.setResultType(rs.getString("result_type"));
        return e;

    }
}
