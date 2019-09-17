package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.SCGEGroup;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 9/10/2019.
 */
public class GroupQuery extends MappingSqlQuery {
    public GroupQuery(DataSource ds, String query){
         super(ds,query);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SCGEGroup g=new SCGEGroup();
        g.setGroupId(rs.getInt("group_id"));
        g.setGroupName(rs.getString("group_name"));
        g.setGroupShortName(rs.getString("group_short_name"));
        g.setGroupType(rs.getString("group_type"));
        return g;
    }
}
