package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Tier;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TierQuery extends MappingSqlQuery {
    public TierQuery(DataSource ds, String sql) {
        super(ds,sql);
    }

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        Tier tier=new Tier();
        tier.setScgeId((rs.getLong("scge_id")));
        tier.setTier(rs.getInt("tier"));
        tier.setLastModifiedBy(rs.getString("last_modified_by"));
        tier.setLastModifiedDate(rs.getDate("last_modified_date"));
        return tier;
    }
}
