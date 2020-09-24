package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Experiment;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 9/10/2019.
 */
public class ExperimentQuery extends MappingSqlQuery {
    public ExperimentQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Experiment e= new Experiment();
        e.setId(rs.getInt("experiment_id"));
        e.setName(rs.getString("experiment_name"));
        e.setEditorId(rs.getInt("editor_id"));
        e.setDeliverySystemId((rs.getInt("delivery_system_id")));
        e.setCellLineId(rs.getInt("cellline_id"));
        e.setReporterAnimalId(rs.getInt("reporter_animal_id"));
        e.setGuideId(rs.getInt("guide_id"));
        e.setTargetId(rs.getInt("target_id"));
        e.setApplicationId(rs.getInt("application_id"));
        e.setTestId(rs.getInt("test_id"));
        e.setResultId(rs.getInt("result_id"));
        return e;
    }
}
