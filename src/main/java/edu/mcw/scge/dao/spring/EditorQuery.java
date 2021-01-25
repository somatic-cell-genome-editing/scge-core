package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.datamodel.Access;
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
        e.setId(rs.getInt("editor_id"));
        e.setType(rs.getString("type"));
        e.setSubType(rs.getString("subtype"));
        e.setSymbol(rs.getString("symbol"));
        e.setAlias(rs.getString("alias"));
        e.setSpecies(rs.getString("species"));
        e.setEditorVariant(rs.getString("editor_variant"));
        e.setAccession(rs.getString("accession"));
        e.setPamPreference(rs.getString("pam_preference"));
        e.setSubstrateTarget(rs.getString("substrate_target"));
        e.setActivity(rs.getString("activity"));
        e.setOverhang(rs.getString("overhang"));
        e.setNote(rs.getString("note"));
        e.setReference(rs.getString("reference"));
        e.setAddGeneLink(rs.getString("add_gene_link"));
        e.setFusion(rs.getString("fusion"));
        e.setDsbCleavageType(rs.getString("dsb_cleavage_type"));
        e.setTarget_sequence(rs.getString("target_sequence"));
        e.setProteinFormat(rs.getString("protein_format"));
        e.setSource(rs.getString("source"));
        e.setProteinSequence(rs.getString("protein_sequence"));
        return e;
    }

    public static List<Editor> execute(AbstractDAO dao, String sql, Object... params) throws Exception {
        EditorQuery q = new EditorQuery(dao.getDataSource(), sql);
        return dao.execute(q, params);
    }
}
