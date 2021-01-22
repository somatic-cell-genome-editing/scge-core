package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.EditorQuery;
import edu.mcw.scge.dao.spring.VectorQuery;
import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Vector;

import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class VectorDao extends AbstractDAO {
    public List<Vector> getAllVectors() throws Exception {
        String sql="select * from vector";
        VectorQuery q=new VectorQuery(this.getDataSource(), sql);
        return (List<Vector>)q.execute();
    }
    public List<Vector> getVectorById(int id) throws Exception {
        String sql="select * from vector where vector_id=?";
        VectorQuery q=new VectorQuery(this.getDataSource(), sql);
        return execute(q, id);
    }
}
