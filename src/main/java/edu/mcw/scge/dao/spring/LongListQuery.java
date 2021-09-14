package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LongListQuery extends MappingSqlQuery {
    public LongListQuery(DataSource ds, String query) {
        super(ds, query);
    }

    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getLong(1);
    }
    public static List<Long> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        LongListQuery q = new LongListQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
