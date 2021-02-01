package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.EditorQuery;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.datamodel.Editor;

import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class EditorDao extends AbstractDAO {
    public List<Editor> getAllEditors() throws Exception {
        String sql="select * from editor";
        EditorQuery q=new EditorQuery(this.getDataSource(), sql);
        return (List<Editor>)q.execute();
    }
    public List<Editor> getEditorById(int id) throws Exception {
        String sql="select * from editor where editor_id=?";
        EditorQuery q=new EditorQuery(this.getDataSource(), sql);
        return execute(q, id);
    }
	public List<Editor> getEditorByGuide(int guideId) throws Exception {
        String sql="select ed.* from experiment_record e, editor ed where e.editor_id=ed.editor_id and e.guide_id=?";
        EditorQuery q=new EditorQuery(this.getDataSource(), sql);
        return execute(q, guideId);
	}
    public int insertEditor(Editor editor) throws Exception{

        String sql = "insert into editor ( editor_id, subtype, species, pam_preference, editor_variant, \n" +
                "fusion, activity, dsb_cleavage_type, target_sequence, protein_format,source, \n" +
                "type, symbol, alias, accession, substrate_target, overhang, note, " +
                "reference, add_gene_link, protein_sequence ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int editorId = this.getNextKeyFromSequence("editor_seq");


        update(sql, editorId,editor.getSubType(),editor.getSpecies(),editor.getPamPreference(),
                editor.getEditorVariant(),editor.getFusion(),editor.getActivity(),editor.getDsbCleavageType(),
                editor.getTarget_sequence(),editor.getProteinFormat(),editor.getSource(),editor.getType(),
                editor.getSymbol(),editor.getAlias(),editor.getAccession(),editor.getSubstrateTarget(),
                editor.getOverhang(),editor.getNote(),editor.getReference(),editor.getAddGeneLink(), editor.getProteinSequence());

        return editorId;
    }

    public int getEditorId(Editor editor) throws Exception {

        String sql = "select * from editor where species =? and type=? and subtype=? and symbol = ?  and alias=?";

        List<Editor> list = EditorQuery.execute(this,sql,editor.getSpecies(), editor.getType(),editor.getSubType(),editor.getSymbol(),editor.getAlias() );
        return list.isEmpty() ? 0 : list.get(0).getId();
    }
}
