package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class IntListQuery extends MappingSqlQuery {

    public IntListQuery(DataSource ds, String query) {
        super(ds, query);
    }

    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
    }

    public static List<Integer> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        IntListQuery q = new IntListQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
