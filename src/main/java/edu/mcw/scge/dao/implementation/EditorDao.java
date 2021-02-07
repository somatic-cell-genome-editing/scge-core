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
                "fusion, activity, dsb_cleavage_type, target_sequence, source, \n" +
                "type, symbol, alias,  substrate_target, protein_sequence, editor_description, annotated_map ) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int editorId = this.getNextKeyFromSequence("editor_seq");


        update(sql, editorId,editor.getSubType(),editor.getSpecies(),editor.getPamPreference(),
                editor.getEditorVariant(),editor.getFusion(),editor.getActivity(),editor.getDsbCleavageType(),
                editor.getTarget_sequence(),editor.getSource(),editor.getType(),
                editor.getSymbol(),editor.getAlias(),editor.getSubstrateTarget(), editor.getProteinSequence(),
                editor.getEditorDescription(),editor.getAnnotatedMap());

        return editorId;
    }

    public int getEditorId(Editor editor) throws Exception {

        String sql = "select * from editor where species =? and type=? and subtype=? and symbol = ?";

        List<Editor> list = EditorQuery.execute(this,sql,editor.getSpecies(), editor.getType(),editor.getSubType(),editor.getSymbol() );
        return list.isEmpty() ? 0 : list.get(0).getId();
    }
    public boolean verifyEditorAccess(int editorId, int personId) throws Exception {
        String sql="(select e.* from editor e left outer join experiment_record r on e.editor_id=r.editor_id\n" +
                "left outer join experiment x on x.experiment_id=r.experiment_id " +
                "left outer join study s on s.study_id =x.study_id " +
                "left outer join person_info p on p.group_id=s.group_id " +
                "where p.person_id=? and e.editor_id=? ) union " +
                "(select ed.* from editor ed  where ed.tier=4 and ed.editor_id=?)";
        EditorQuery q=new EditorQuery(this.getDataSource(), sql);
        List<Editor> editorList= execute(q, personId, editorId, editorId);
        return editorList.size() > 0;
    }
}
