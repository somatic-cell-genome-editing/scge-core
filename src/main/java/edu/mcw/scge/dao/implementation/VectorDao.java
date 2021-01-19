package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.EditorQuery;
import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Vector;

import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class VectorDao extends AbstractDAO {
    public List<Vector> getAllVectors() throws Exception {
        String sql="select * from vector";
        EditorQuery q=new EditorQuery(this.getDataSource(), sql);
        return (List<Editor>)q.execute();
    }
    public List<Vector> getVectorById(int id) throws Exception {
        String sql="select * from vector where vector_id=?";
        EditorQuery q=new EditorQuery(this.getDataSource(), sql);
        return execute(q, id);
    }
}
