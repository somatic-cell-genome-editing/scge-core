package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Model;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        m.setRrid(rs.getString("rrid"));
        m.setSource(rs.getString("source"));
        m.setSubtype(rs.getString("subtype"));
        m.setTransgene(rs.getString("transgene"));
        m.setTransgeneDescription(rs.getString("transgene_description"));
        m.setTransgeneReporter(rs.getString("transgene_reporter"));
        m.setReporterDbIds(rs.getString("reporter_db_ids"));
        m.setAnnotatedMap(rs.getString("annotated_map"));
        return m;
    }

    public static List<Model> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        ModelQuery q = new ModelQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
