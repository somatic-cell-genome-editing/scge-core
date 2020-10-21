package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.ApplicationMethod;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        a.setTimeCourse(rs.getString("time_course"));
        return a;
    }
}
