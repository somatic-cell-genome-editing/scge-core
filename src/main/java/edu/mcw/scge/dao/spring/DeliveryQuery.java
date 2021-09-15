package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Delivery;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DeliveryQuery extends MappingSqlQuery {
    public DeliveryQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Delivery d=new Delivery();
        d.setId(rs.getLong("ds_id"));
        d.setType(rs.getString("ds_type"));
        d.setSubtype(rs.getString("ds_subtype"));
        d.setName(rs.getString("ds_name"));
        d.setSource(rs.getString("ds_source"));
        d.setDescription(rs.getString("ds_description"));
        d.setLabId(rs.getString("ds_lab_id"));
        d.setAnnotatedMap(rs.getString("ds_annotated_map"));
        d.setRrid(rs.getString("ds_rrid"));
        d.setNpSize(rs.getString("ds_np_size"));
        d.setMolTargetingAgent(rs.getString("ds_mol_targeting_agent"));
        d.setTier(rs.getInt("tier"));
        return d;
    }

    public static List<Delivery> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        DeliveryQuery q = new DeliveryQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}