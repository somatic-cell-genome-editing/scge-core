package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.TierQuery;
import edu.mcw.scge.datamodel.Tier;

import java.util.List;

public class TierDao extends AbstractDAO {
    public int getTier(long scgeId) throws Exception {
        String sql="select tier from scge_tier where scge_id=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        List<Integer> tiers=execute(query, scgeId);
        return tiers.size()>0?tiers.get(0):0;
    }
    public void update(Tier tier) throws Exception {
        String sql="update scge_tier set " +
                "tier=? ," +
                "last_modified_by=?," +
                "last_modified_date=? where scge_id=?" ;
        update(sql, tier.getTier(), tier.getLastModifiedBy(), tier.getLastModifiedDate(),tier.getScgeId());
    }
}
