package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.Access;
import edu.mcw.scge.datamodel.Editor;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jthota on 9/10/2019.
 */
public class EditorQuery extends MappingSqlQuery {
    public EditorQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Editor e= new Editor();
        e.setId(rs.getInt("editor_id"));
        e.setType(rs.getString("type"));
        e.setSubType(rs.getString("subtype"));
        e.setSpecies(rs.getString("species"));
        e.setPamPreference(rs.getString("pam_preference"));
        e.setEditorVariant(rs.getString("editor_variant"));
        e.setFusion(rs.getString("fustion"));
        e.setActivity(rs.getString("activity"));
        e.setDsbCleavageType(rs.getString("dsb_cleavage_type"));
        e.setTarget_sequence(rs.getString("target_sequence"));
        e.setProteinFormat(rs.getString("protein_format"));
        e.setSource(rs.getString("source"));
        return e;
    }
}
