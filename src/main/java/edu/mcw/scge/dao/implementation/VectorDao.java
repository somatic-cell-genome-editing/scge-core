package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.VectorQuery;
import edu.mcw.scge.datamodel.Vector;

import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class VectorDao extends AbstractDAO {
    public List<Vector> getAllVectors() throws Exception {
        String sql="select * from vector order by name";
        VectorQuery q=new VectorQuery(this.getDataSource(), sql);
        return (List<Vector>)q.execute();
    }
    public List<Vector> getDistinctVectorsByExperimentId(long experimentId) throws Exception {
        String sql="   select * from vector where vector_id in (" +
                "            select vector_id from vector_associations where experiment_record_id in  (" +
                "            select experiment_record_id from experiment_record where experiment_id=?)) order by name";
        VectorQuery q=new VectorQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<Vector> getVectorById(long id) throws Exception {
        String sql="select * from vector where vector_id=?";
        VectorQuery q=new VectorQuery(this.getDataSource(), sql);
        return execute(q, id);
    }

    public long insertVector(Vector vector) throws Exception{

        String sql = "insert into vector ( name,vector_id,type,subtype,genome_serotype," +
                "description,capsid_variant,source,lab_id,annotated_map,titer_method," +
                "capsid_serotype ) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        long vectorId = this.getNextKeyFromSequenceLong("vector_seq");

        update(sql, vector.getName(),vectorId,vector.getType(),vector.getSubtype(),vector.getGenomeSerotype(),
                vector.getDescription(),vector.getCapsidVariant(),vector.getSource(),vector.getLabId(),vector.getAnnotatedMap(),
                vector.getTiterMethod(),vector.getCapsidSerotype());

        return vectorId;
    }
    public void updateVector(Vector vector) throws Exception{

        String sql = "update vector set name=?,type=?,subtype=?,genome_serotype=?," +
                "description=?,capsid_variant=?,source=?,lab_id=?,annotated_map=?,titer_method=?," +
                "capsid_serotype=? where vector_id=?";


        update(sql, vector.getName(),vector.getType(),vector.getSubtype(),vector.getGenomeSerotype(),
                vector.getDescription(),vector.getCapsidVariant(),vector.getSource(),vector.getLabId(),vector.getAnnotatedMap(),
                vector.getTiterMethod(),vector.getCapsidSerotype(),vector.getVectorId());
    }
    public void updateVectorTier(int updatedTier, long vectorId) throws Exception {
        String sql="update vector set tier=? where vector_id=?";
        update(sql,updatedTier, vectorId);
    }
    public long getVectorId(Vector vector) throws Exception {

        String sql = "select * from vector where subtype=? and name = ?";

        List<Vector> list = VectorQuery.execute(this,sql, vector.getSubtype(),vector.getName());
        return list.isEmpty() ? 0 : list.get(0).getVectorId();
    }
    public List<Vector> getVectorsByExpRecId(long expRecId) throws Exception {
        String sql="select distinct v.* from vector v inner join vector_associations va on v.vector_id = va.vector_id where va.experiment_record_id=?";
        VectorQuery q=new VectorQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }
    public void insertVectorAssoc(long expRecId,long vectorId) throws Exception{
        String sql = "insert into vector_associations ( experiment_record_id, vector_id ) values (?,?)";

        update(sql,expRecId,vectorId);
    }

}
