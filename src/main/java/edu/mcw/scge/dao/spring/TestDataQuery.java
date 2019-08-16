package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.TestData;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 8/16/2019.
 */
public class TestDataQuery extends MappingSqlQuery {
    public TestDataQuery(DataSource ds, String query){
        super(ds,query);
    }
    @Override
    protected TestData mapRow(ResultSet rs, int rowNum) throws SQLException {
        TestData td= new TestData();
        td.setSymbol(rs.getString("symbol"));
        td.setName(rs.getString("name"));

        return td;
    }
}
