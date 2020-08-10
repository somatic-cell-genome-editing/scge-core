package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Access;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 9/10/2019.
 */
public class AccessQuery extends MappingSqlQuery {
    public AccessQuery (DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
         Access a= new Access();
        a.setAccessId(rs.getInt("access_id"));
        a.setAccess(rs.getString("access_level"));
        return a;
    }
}
