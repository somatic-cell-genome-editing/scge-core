package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Reference;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenceQuery extends MappingSqlQuery {
    public ReferenceQuery(DataSource ds, String query) {
        super(ds, query);
    }

    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        Reference ref = new Reference();

        ref.setKey(rs.getInt("ref_key"));
        ref.setTitle(rs.getString("title"));

        ref.setVolume(rs.getString("volume"));
        ref.setIssue(rs.getString("issue"));
        ref.setPages(rs.getString("pages"));
        ref.setPubStatus(rs.getString("pub_status"));
        ref.setPubDate(rs.getDate("pub_date"));

        ref.setNotes(rs.getString("notes"));
        ref.setReferenceType(rs.getString("reference_type"));
        ref.setCitation(rs.getString("citation"));
        ref.setRefAbstract(rs.getString("abstract"));
        ref.setPublisher(rs.getString("publisher"));
        ref.setPublisherCity(rs.getString("publisher_city"));
        ref.setUrlWebReference(rs.getString("url_web_reference"));
        ref.setSpeciesTypeKey(rs.getInt("species_type_key"));
        ref.setDoi(rs.getString("doi"));

        return ref;
    }

}
