package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Model;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelQuery extends MappingSqlQuery {
    public ModelQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Model m=new Model();
        m.setModelId(rs.getInt("model_id"));
        m.setName(rs.getString("name"));
        m.setAge(rs.getString("age"));
        m.setAgeRange(rs.getString("age_range"));
        m.setOrganism(rs.getString("organism"));
        m.setGenotype(rs.getString("genotype"));
        m.setShortName(rs.getString("shot_name"));
        m.setSex(rs.getString("sex"));
        m.setType(rs.getString("type"));
        m.setStockNumber(rs.getString("stock_number"));

        return m;
    }
}
