package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Vector;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        v.setVectorId(rs.getInt("vector_id"));
        v.setType(rs.getString("type"));
        v.setSubtype(rs.getString("subtype"));
        v.setAlias(rs.getString("alias"));
        v.setAnnotatedMap(rs.getString("annotated_map"));
        v.setSource(rs.getString("source"));
        v.setStockNumber(rs.getString("stock_number"));
        v.setVariant(rs.getString("variant"));
        return v;
    }
}
