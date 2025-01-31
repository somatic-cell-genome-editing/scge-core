package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.AntibodyQuery;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.dao.spring.LongListQuery;
import edu.mcw.scge.datamodel.Antibody;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.List;

public class AntibodyDao extends AbstractDAO {
    public List<Antibody> getAntibodies() throws Exception{
        String sql="select * from antibody";
        AntibodyQuery q=new AntibodyQuery(this.getDataSource(), sql);
        return q.execute();
    }
    public List<Antibody> getAntibody(int antibodyId) throws Exception{
        String sql="select * from antibody where antibody_id=?";
        AntibodyQuery q=new AntibodyQuery(this.getDataSource(), sql);
        return execute(q, antibodyId);
    }
    public List<Antibody> getDistinctAntibodyByExperimentId(long experimentId) throws Exception{
        String sql="s select * from antibody where antibody_id in (" +
                "            select antibody_id from antibody_associations where experiment_record_id in  (" +
                "            select experiment_record_id from experiment_record where experiment_id=?))";
        AntibodyQuery q=new AntibodyQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public int insertAntibody(Antibody antibody) throws Exception{

        String sql = "insert into antibody(antibody_id,rrid,other_id,antibody_description ) values (?,?,?,?)";

        int antibodyId = this.getNextKeyFromSequence("antibody_seq");


        update(sql, antibodyId, antibody.getRrid(),antibody.getOtherId(),antibody.getDescription());

        return antibodyId;
    }
    public void insertAntibodyAssoc(long expRecId,long antibodyId) throws Exception{
        String sql = "insert into antibody_associations ( experiment_record_id, antibody_id ) values (?,?)";

        update(sql,expRecId,antibodyId);
    }

    public int getAntibodyId(Antibody antibody) throws Exception {

        String sql = "select * from antibody where rrid=? and other_id=? ";

        List<Antibody> list = AntibodyQuery.execute(this,sql, antibody.getRrid(),antibody.getOtherId());
        return list.isEmpty() ? 0 : list.get(0).getAntibodyId();
    }
    public List<Antibody> getAntibodysByExpRecId(long expRecId) throws Exception {
        String sql="select distinct a.* from antibody a inner join antibody_associations aa on" +
                " a.antibody_id = aa.antibody_id where aa.experiment_record_id=?";
        AntibodyQuery q=new AntibodyQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }
    public List<ExperimentRecord> getAssociatedExperimentRecords(int antibodyId) throws Exception {
        String sql="select * from experiment_record where experiment_record_id in (" +
                "select experiment_record_id from antibody_associations where antibody_id=?)";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, antibodyId);
    }
}
