package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.HRDonor;
import edu.mcw.scge.datamodel.PeptideNucleicAcid;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hsnalabolu on 2/4/2021.
 */
public class HRDonorQuery extends MappingSqlQuery {
    public HRDonorQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        HRDonor h = new HRDonor();
        h.setId(rs.getLong("hrdonor_id"));
        h.setSequence(rs.getString("sequence"));
        h.setSource(rs.getString("source"));
        h.setLabId(rs.getString("lab_id"));
        h.setDescription(rs.getString("description"));
        h.setModification(rs.getString("modification"));
        h.setType(rs.getString("type"));
        h.setTier(rs.getInt("tier"));
        return h;
    }

    public static List<HRDonor> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        HRDonorQuery q = new HRDonorQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
