package edu.mcw.scge.dao.spring;

import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 8/26/2019.
 */
public class CountQuery extends MappingSqlQuery {
    public CountQuery(DataSource ds, String query){
        super(ds, query);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Integer.valueOf(rs.getInt(1));
    }
    public int getCount() throws Exception {
        return ((Integer)this.execute().get(0)).intValue();
    }

    public int getCount(Object[] params) throws Exception {
        return ((Integer)this.execute(params).get(0)).intValue();
    }
}
