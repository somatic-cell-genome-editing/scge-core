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
        obj.setGroup(rs.getString("group_name"));
        obj.setSubgroup(rs.getString("subgroup_name"));
        obj.setRole(rs.getString("role"));
        return obj;
    }
}
