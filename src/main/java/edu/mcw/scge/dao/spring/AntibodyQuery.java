package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Antibody;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AntibodyQuery extends MappingSqlQuery {
    public AntibodyQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Antibody a= new Antibody();
        a.setAntibodyId(rs.getInt("antibody_id"));
        a.setRrid(rs.getString("rrid"));
        a.setOtherId(rs.getString("other_id"));
        a.setDescription(rs.getString("antibody_description"));
        return a;
    }

    public static List<Antibody> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        AntibodyQuery q = new AntibodyQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
