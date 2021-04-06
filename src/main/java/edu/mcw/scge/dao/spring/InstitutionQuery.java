package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.InstitutionDAO;
import edu.mcw.scge.dao.implementation.PersonDao;
import edu.mcw.scge.datamodel.Institution;
import edu.mcw.scge.datamodel.Person;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 8/20/2019.
 */
public class InstitutionQuery extends MappingSqlQuery {
    InstitutionDAO pdao=new InstitutionDAO();
    public InstitutionQuery(DataSource ds, String query){

        super(ds,query);
    }
    @Override
    protected Institution mapRow(ResultSet rs, int rowNum) throws SQLException {
        Institution i = new Institution();

        i.setId(rs.getInt("institution_id"));
        i.setName(rs.getString("institution_name"));

        return i;

    }
}
