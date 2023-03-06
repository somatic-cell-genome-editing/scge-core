package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.HRDonorQuery;
import edu.mcw.scge.datamodel.HRDonor;

import java.util.List;

/**
 * Created by hsnalabolu on 2/5/2021.
 */
public class HRDonorDao extends AbstractDAO {
    public List<HRDonor> getAllHRDonors() throws Exception {
        String sql="select * from HR_Donor";
        HRDonorQuery q=new HRDonorQuery(this.getDataSource(), sql);
        return (List<HRDonor>)q.execute();
    }
    public List<HRDonor> getHRDonorById(long id) throws Exception {
        String sql="select * from HR_Donor where hrdonor_id=?";
        HRDonorQuery q=new HRDonorQuery(this.getDataSource(), sql);
        return execute(q, id);
    }

    public long insertHRDonor(HRDonor hrdonor) throws Exception{

        String sql = "insert into hr_donor (hrdonor_id,type,lab_id,source,sequence,modification,description,tier)" +
                " values (?,?,?,?,?,?,?,?)";

        long donorId = this.getNextKeyFromSequenceLong("donor_seq");
        hrdonor.setId(donorId);

        update(sql, donorId,hrdonor.getType(),hrdonor.getLabId(),hrdonor.getSource(),hrdonor.getSequence(),
                hrdonor.getModification(), hrdonor.getDescription(), hrdonor.getTier());

        return donorId;
    }

    public long getHRDonorId(HRDonor hrdonor) throws Exception {

        // modification could be null
        String sql = "SELECT MAX(hrdonor_id) FROM hr_donor WHERE sequence=? AND COALESCE(modification,'*')=COALESCE(?,'*')";

        return getLongCount(sql, hrdonor.getSequence(), hrdonor.getModification());
    }
}
