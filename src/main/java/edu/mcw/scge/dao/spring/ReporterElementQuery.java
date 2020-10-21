package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.ReporterElement;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporterElementQuery extends MappingSqlQuery {
    public ReporterElementQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReporterElement e=new ReporterElement();
        e.setReporterElementId(rs.getInt("reporter_element_id"));
        e.setReporterName(rs.getString("reporter_name"));
        e.setReporterType(rs.getString("reporter_type"));
        e.setOrganism(rs.getString("organism"));
        e.setReporterProteinId(rs.getString("reporter_protein_id"));
        return e;
    }
}
