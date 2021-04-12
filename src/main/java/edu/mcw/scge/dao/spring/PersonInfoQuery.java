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
        obj.setGroupType(rs.getString("group_type"));
        //obj.setSubGroupId(rs.getInt("subgroup_id"));
        //obj.setSubGroupName(rs.getString("subgroup_name"));
        try {
            obj.setGrantId(rs.getInt("grant_id"));
        }catch (Exception e){ }
        try {
            if (rs.getString("grant_title") != null);
        }catch (Exception e){}
        try {
            obj.setGrantTitle(rs.getString("grant_title"));
        }catch (Exception e){}
        try {
            if (rs.getString("grant_initiative") != null)
                obj.setGrantInitiative(rs.getString("grant_initiative"));
        }catch (Exception e){}
        obj.setRole(rs.getString("role"));
        return obj;
    }
}
