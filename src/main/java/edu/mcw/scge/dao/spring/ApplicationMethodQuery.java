package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.ApplicationMethod;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ApplicationMethodQuery extends MappingSqlQuery {
    public ApplicationMethodQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ApplicationMethod a= new ApplicationMethod();
        a.setApplicationId(rs.getInt("application_method_id"));
        a.setApplicationType(rs.getString("application_type"));
        a.setDaysPostInjection(rs.getString("days_post_injection"));
        a.setSiteOfApplication(rs.getString("site_of_application"));
        a.setDosage(rs.getString("dosage"));
        a.setEditorFormat(rs.getString("editor_format"));
        a.setInjectionRate(rs.getString("injection_rate"));
        a.setInjectionFrequency(rs.getString("injection_frequency"));
        a.setInjectionVolume(rs.getString("injection_volume"));
        a.setAntidoteId(rs.getString("antidote_id"));
        a.setAntidoteDescription(rs.getString("antidote_description"));
        return a;
    }

    public static List<ApplicationMethod> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        ApplicationMethodQuery q = new ApplicationMethodQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
