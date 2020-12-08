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
        d.setId(rs.getInt("ds_id"));
        d.setType(rs.getString("ds_type"));
        d.setSubtype(rs.getString("ds_sybtype"));
        d.setName(rs.getString("ds_name"));
        d.setSource(rs.getString("ds_source"));
        d.setDescription(rs.getString("description"));
        d.setVvGenomeSerotype(rs.getString("ds_vv_genome_serotype"));
        d.setVvCapsidSerotype(rs.getString("ds_vv_capsid_serotype"));
        d.setLabId(rs.getString("ds_lab_id"));
        d.setVvCapsidVariant(rs.getString("ds_vv_capsid_variant"));
        d.setAnnotatedMap(rs.getString("ds_annotated_map"));
        d.setTiterMethod(rs.getString("ds_titer_method"));
        d.setRrid(rs.getString("ds_rrid"));
        d.setNpSize(rs.getString("np_size"));
        d.setMolTargetingAgent(rs.getString("ds_mol_targeting_agent"));
        return d;
    }
}
