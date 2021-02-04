package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.PersonInfo;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 10/9/2019.
 */
public class PersonInfoQuery extends MappingSqlQuery {
    public PersonInfoQuery(DataSource ds, String sql){
        super(ds, sql);
    }

    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        PersonInfo obj=new PersonInfo();
        obj.setPersonId(rs.getInt("person_id"));
        obj.setGroupId(rs.getInt("group_id"));
        obj.setGroupName(rs.getString("group_name"));
        obj.setSubGroupId(rs.getInt("subgroup_id"));
        obj.setSubGroupName(rs.getString("subgroup_name"));
        obj.setGrantId(rs.getInt("grant_id"));
        obj.setGrantTitle(rs.getString("grant_title"));
        obj.setGrantInitiative(rs.getString("grant_initiative"));
        obj.setRole(rs.getString("role"));
        return obj;
    }
}
