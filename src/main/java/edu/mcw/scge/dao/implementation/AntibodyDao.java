package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.AntibodyQuery;
import edu.mcw.scge.datamodel.Antibody;

import java.util.List;

public class AntibodyDao extends AbstractDAO {
    public List<Antibody> getAntibody(int antibodyId) throws Exception{
        String sql="select * from antibody where antibody_id=?";
        AntibodyQuery q=new AntibodyQuery(this.getDataSource(), sql);
        return execute(q, antibodyId);
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
}
