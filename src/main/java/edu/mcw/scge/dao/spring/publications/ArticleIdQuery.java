package edu.mcw.scge.dao.spring.publications;

import edu.mcw.scge.dao.DataSourceFactory;
import edu.mcw.scge.datamodel.publications.ArticleId;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ArticleIdQuery extends MappingSqlQuery {
    public ArticleIdQuery(DataSource ds,String sql){
        super(ds, sql);
    }
    @Override

    protected ArticleId mapRow(ResultSet rs, int rowNum) throws SQLException {
        /*Map<String, String> map= new HashMap<>();
        map.put(rs.getString("identifier"), rs.getString("url"));
        return map;*/
        ArticleId id=new ArticleId();
        id.setId(rs.getString("identifier"));
        id.setIdType(rs.getString("identifier_type"));
        id.setUrl(rs.getString("url"));

        return id;
    }
}
