package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Vector;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class VectorQuery extends MappingSqlQuery {
    public VectorQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vector v= new Vector();
        v.setName(rs.getString("name"));
        v.setVectorId(rs.getLong("vector_id"));
        v.setType(rs.getString("type"));
        v.setSubtype(rs.getString("subtype"));
        v.setAnnotatedMap(rs.getString("annotated_map"));
        v.setSource(rs.getString("source"));
        v.setGenomeSerotype(rs.getString("genome_serotype"));
        v.setCapsidSerotype(rs.getString("capsid_serotype"));
        v.setCapsidVariant(rs.getString("capsid_variant"));
        v.setTiterMethod(rs.getString("titer_method"));
        v.setLabId(rs.getString("lab_id"));
        v.setDescription(rs.getString("description"));
        v.setTier(rs.getInt("tier"));
        return v;
    }

    public static List<Vector> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        VectorQuery q = new VectorQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
