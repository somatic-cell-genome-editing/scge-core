package edu.mcw.scge.dao.spring.publications;

import edu.mcw.scge.dao.implementation.PublicationDAO;
import edu.mcw.scge.datamodel.publications.Reference;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ReferenceQuery extends MappingSqlQuery {
    PublicationDAO publicationDAO=new PublicationDAO();
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

        ref.setDoi(rs.getString("doi"));
        try {
            ref.setPubmedId(publicationDAO.getPubmedId(ref.getKey()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            if(rs.getString("mesh_terms")!=null && !rs.getString("mesh_terms").equals("")){
                String[] meshTerms=rs.getString("mesh_terms").split(",");
                ref.setMeshTerms(Arrays.asList(meshTerms));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref;
    }


}
