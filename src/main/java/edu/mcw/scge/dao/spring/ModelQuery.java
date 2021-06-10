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
        m.setModelId(rs.getLong("model_id"));
        m.setName(rs.getString("name"));
        m.setOrganism(rs.getString("organism"));
        m.setSex(rs.getString("sex"));
        m.setType(rs.getString("type"));
        m.setRrid(rs.getString("rrid"));
        m.setSource(rs.getString("source"));
        m.setSubtype(rs.getString("subtype"));
        m.setTransgene(rs.getString("transgene"));
        m.setTransgeneDescription(rs.getString("transgene_description"));
        m.setTransgeneReporter(rs.getString("transgene_reporter"));
        m.setAnnotatedMap(rs.getString("annotated_map"));
        m.setParentalOrigin(rs.getString("parental_origin"));
        m.setStrainCode(rs.getString("strain_code"));
      //  m.setStrainAlias(rs.getString("starin_alias"));
        m.setDescription(rs.getString("model_description"));
        m.setTier(rs.getInt("tier"));
        return m;
    }

    public static List<Model> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        ModelQuery q = new ModelQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
