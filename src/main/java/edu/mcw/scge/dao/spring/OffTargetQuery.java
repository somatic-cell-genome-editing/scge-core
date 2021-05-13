package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.OffTarget;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OffTargetQuery extends MappingSqlQuery<OffTarget> {
    public OffTargetQuery(DataSource ds, String sql){
        super(ds,sql);
    }

    @Override
    protected OffTarget mapRow(ResultSet rs, int rowNum) throws SQLException {

        OffTarget o = new OffTarget();
        o.setGuideId(rs.getInt("guide_id"));
        o.setDetectionMethod(rs.getString("detection_method"));
        o.setNoOfSitesDetected(rs.getInt("no_of_sites_detected"));
        return o;
    }
}



