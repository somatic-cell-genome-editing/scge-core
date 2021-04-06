package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Grant;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GrantQuery extends MappingSqlQuery {
    public GrantQuery(DataSource ds, String sql){
        super(ds,sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Grant g= new Grant();
        g.setGrantId(rs.getInt("grant_id"));
        g.setGroupId(rs.getInt("group_id"));
        g.setGrantNumber(rs.getString("grant_number"));
        g.setGrantTitle(rs.getString("grant_title"));
        g.setGrantTitleLc(rs.getString("grant_title_lc"));
        g.setGrantInitiative(rs.getString("grant_initiative"));
        return g;
    }
}
