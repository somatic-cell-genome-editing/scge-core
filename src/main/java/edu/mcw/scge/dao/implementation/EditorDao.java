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
        String sql="select ed.* from experiment e, editor ed where e.editor_id=ed.editor_id and e.guide_id=?";
        EditorQuery q=new EditorQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }
}
