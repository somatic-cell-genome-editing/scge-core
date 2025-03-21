package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Protocol;
import org.springframework.jdbc.object.MappingSqlQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProtocolQuery extends MappingSqlQuery<Protocol> {
    public ProtocolQuery(DataSource ds, String sql) {
        super(ds, sql);
    }

    @Override
    protected Protocol mapRow(ResultSet rs, int rowNum) throws SQLException {

        Protocol p = new Protocol();
        p.setDescription(rs.getString("description"));
        p.setId(rs.getLong("protocol_id"));
        p.setTitle(rs.getString("title"));
        p.setTier(rs.getInt("tier"));
        p.setFilename(rs.getString("filename"));
        p.setXref(rs.getString("xref"));
        p.setKeywords(rs.getString("keywords"));
        try {
            p.setAssociatedObjectId(rs.getLong("object_scge_id"));
        }catch (Exception e){}
        return p;
    }

    public static List<Protocol> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        ProtocolQuery q = new ProtocolQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
