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
        d.setDeliverySystemId(rs.getInt("delivery_system_id"));
        d.setDeliverySystemType(rs.getString("delivery_system_type"));
        d.setDeliverySystemSubtype(rs.getString("delivery_system_subtype"));
        return d;
    }
}