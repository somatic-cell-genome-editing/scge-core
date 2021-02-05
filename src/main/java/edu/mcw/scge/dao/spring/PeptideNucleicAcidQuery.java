package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.PeptideNucleicAcid;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hsnalabolu on 2/4/2021.
 */
public class PeptideNucleicAcidQuery extends MappingSqlQuery {
    public PeptideNucleicAcidQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        PeptideNucleicAcid p= new PeptideNucleicAcid();
        p.setId(rs.getInt("pna_id"));
        p.setTargetSequence(rs.getString("target_sequence"));
        p.setSequence(rs.getString("sequence"));
        p.setOrientation(rs.getString("orientation"));
        p.setSource(rs.getString("source"));
        p.setLabId(rs.getString("lab_id"));
        p.setDescription(rs.getString("description"));
        return p;
    }

    public static List<PeptideNucleicAcid> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        PeptideNucleicAcidQuery q = new PeptideNucleicAcidQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
