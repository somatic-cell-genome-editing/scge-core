package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Guide;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideQuery extends MappingSqlQuery<Guide> {
    public GuideQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Guide mapRow(ResultSet rs, int rowNum) throws SQLException {

        Guide g=new Guide();
        g.setGuide_id(rs.getInt("guide_id"));
        g.setGuide(rs.getString("guide"));
        g.setDetectionMethod(rs.getString("detection_method"));
        g.setgRnaFormat(rs.getString("grna_format"));
        return g;
    }
}
