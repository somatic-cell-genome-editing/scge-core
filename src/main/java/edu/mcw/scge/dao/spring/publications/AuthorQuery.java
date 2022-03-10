package edu.mcw.scge.dao.spring.publications;

import edu.mcw.scge.datamodel.publications.Author;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorQuery extends MappingSqlQuery {
    public AuthorQuery(DataSource ds,String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author=new Author();
        author.setFirstName(rs.getString("author_first_name"));
        author.setLastName(rs.getString("author_last_name"));
        author.setInitials(rs.getString("author_initials"));
        return author;
    }
}
