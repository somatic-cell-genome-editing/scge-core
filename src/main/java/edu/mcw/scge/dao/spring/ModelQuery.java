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
        m.setType(rs.getString("type"));
        m.setName(rs.getString("name"));
        m.setOrganism(rs.getString("organism"));
        m.setAge(rs.getString("age"));
        m.setSex(rs.getString("sex"));
        m.setGenotype(rs.getString("genotype"));
        m.setStockNumber(rs.getString("stock_number"));
        m.setShortName(rs.getString("shot_name"));
        m.setAgeRange(rs.getString("age_range"));
        m.setRrid(rs.getString("rrid"));
        m.setSource(rs.getString("source"));
        m.setTransgene(rs.getString("transgene"));
        m.setSubtype(rs.getString("subtype"));
        m.setAnnotatedMap(rs.getString("annotated_map"));
        m.setTransgeneDescription(rs.getString("transgene_description"));
        m.setTransgeneReporter(rs.getString("transgene_reporter"));
        m.setReporterDbIds(rs.getString("reporter_db_ids"));

        return m;
    }
}
