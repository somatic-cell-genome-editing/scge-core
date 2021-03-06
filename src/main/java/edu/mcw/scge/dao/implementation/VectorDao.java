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

    public int insertVector(Vector vector) throws Exception{

        String sql = "insert into vector ( name,vector_id,type,subtype,genome_serotype," +
                "description,capsid_variant,source,lab_id,annotated_map,titer_method," +
                "capsid_serotype ) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        int vectorId = this.getNextKeyFromSequence("vector_seq");


        update(sql, vector.getName(),vectorId,vector.getType(),vector.getSubtype(),vector.getGenomeSerotype(),
                vector.getDescription(),vector.getCapsidVariant(),vector.getSource(),vector.getLabId(),vector.getAnnotatedMap(),
                vector.getTiterMethod(),vector.getCapsidSerotype());

        return vectorId;
    }

    public int getVectorId(Vector vector) throws Exception {

        String sql = "select * from vector where subtype=? and capsid_variant = ?";

        List<Vector> list = VectorQuery.execute(this,sql, vector.getSubtype(),vector.getCapsidVariant());
        return list.isEmpty() ? 0 : list.get(0).getVectorId();
    }



}
