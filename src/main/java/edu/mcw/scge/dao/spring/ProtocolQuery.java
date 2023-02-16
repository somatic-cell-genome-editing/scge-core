package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.implementation.TierDao;
import edu.mcw.scge.datamodel.Protocol;
import org.springframework.jdbc.object.MappingSqlQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProtocolQuery extends MappingSqlQuery<Protocol> {
    TierDao tierDao=new TierDao();
    public ProtocolQuery(DataSource ds, String sql) {
        super(ds, sql);
    }

    @Override
    protected Protocol mapRow(ResultSet rs, int rowNum) throws SQLException {

        Protocol p = new Protocol();
        p.setDescription(rs.getString("description"));
        p.setId(rs.getLong("protocol_id"));
        p.setTitle(rs.getString("title"));
        try {
            p.setTier(tierDao.getTier(rs.getLong("protocol_id")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }        p.setFilename(rs.getString("filename"));
        p.setXref(rs.getString("xref"));
        p.setKeywords(rs.getString("keywords"));
        return p;
    }

    public static List<Protocol> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        ProtocolQuery q = new ProtocolQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
