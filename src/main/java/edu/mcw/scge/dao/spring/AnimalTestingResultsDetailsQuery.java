package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.AnimalTestingResultsDetail;
import edu.mcw.scge.datamodel.Sample;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTestingResultsDetailsQuery extends MappingSqlQuery {
    public AnimalTestingResultsDetailsQuery(DataSource ds, String sql){
        super(ds,sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sample s=new Sample();
        s.setExperimentRecordId(rs.getInt("exp_rec_id"));
        s.setSampleId(rs.getInt("sample"));
        s.setSex(rs.getString("sample_sex"));
        s.setSignal(rs.getString("signal"));
        s.setImageLink(rs.getString("image_link"));
        return s;
    }
}
