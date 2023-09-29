package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Editor;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        e.setId(rs.getLong("editor_id"));
        e.setType(rs.getString("type"));
        e.setSubtype(rs.getString("subtype"));
        e.setSymbol(rs.getString("symbol"));
        e.setAlias(rs.getString("alias"));
        e.setSpecies(rs.getString("species"));
        e.setEditorVariant(rs.getString("editor_variant"));
        e.setPamPreference(rs.getString("pam_preference"));
        e.setSubstrateTarget(rs.getString("substrate_target"));
        e.setActivity(rs.getString("activity"));
        e.setFusion(rs.getString("fusion"));
        e.setDsbCleavageType(rs.getString("dsb_cleavage_type"));
        e.setSource(rs.getString("source"));
        e.setProteinSequence(rs.getString("protein_sequence"));
        e.setAnnotatedMap(rs.getString("annotated_map"));
        e.setEditorDescription(rs.getString("editor_description"));
        e.setCatalog(rs.getString("catalog"));
        e.setRrid(rs.getString("rrid"));
        e.setTier(rs.getInt("tier"));
        e.setOrientation(rs.getString("orientation"));

        try {
            e.setTargetLocus(rs.getString("target_locus"));
        } catch(Exception ignored) {
        }

        try {
            e.setTarget_sequence(rs.getString("target_sequence"));
        } catch(Exception ignored) {
        }

        try {
            e.setAssembly(rs.getString("assembly"));
            e.setChr(rs.getString("chromosome"));
            e.setStart(rs.getString("start"));
            e.setStop(rs.getString("stop"));
            e.setStrand(rs.getString("strand"));
        } catch(Exception ignored2) {
        }




        return e;
    }

    public static List<Editor> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        EditorQuery q = new EditorQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
